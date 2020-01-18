/**********************************************************************
작성일 : 2020-01-17
작성자 : 명근
문제 : Traveling salesman problem - 1
출처 : 알고스팟 
풀이 : 다 더하고 크기비교
예상 시간복잡도 : O(N!) = 43020
TEST 결과 : 
알고스팟 테스트 통과
정리전 - 44ms
정리후 - 172ms << node의 visit을 체크하는 부분을 해시로 바꾼게 원인!
node에 visit을 추가하면 - 52ms
**********************************************************************/
#include <iostream>
#include <string>
#include <vector>
#include <stack>
#include <algorithm>
#include <unordered_set>

class Node;
using ID_T = unsigned int;
using NodePtr = Node*;
using Nodes = std::vector<NodePtr>;

// 각 노드를 잇는 경로의 cost를 저장.
// 두 노드의 id를 주면 둘을 잇는 cost를 반환.
class CostMap {
private:
    std::vector<std::vector<double>> costmap;
    static CostMap* instance;
    CostMap(){}
    ~CostMap(){
        for(auto& costs : costmap)
            costs.clear();
        costmap.clear();
    }

public:
    static void Reset(const std::vector<std::vector<double>>& input) {
        delete instance;

        instance = new CostMap();
        instance->costmap = input;
    }

    static const double Get(const ID_T& lhs, const ID_T& rhs) {
        return instance->costmap[lhs][rhs];
    }
};
CostMap* CostMap::instance = nullptr;


// Graph의 노드, 
// 인접 노드를 벡터로 저장하고 모든 인접노드를 순회하는 용도로 사용하도록 만들어짐.
class Node {
public: ID_T id;
public: bool isVisit;

private:
    Nodes nexts;
    Nodes::const_iterator nextIt;

public:
    Node(const ID_T& _id): id(_id), isVisit(false), nextIt(nexts.end()) {}

    ~Node() {
        for(auto& node: this->nexts)
            delete(node);
        this->nexts.clear();
    }

    bool HasNext() {
        if(this->nextIt != this->nexts.end())
            return true;

        this->nextIt = this->nexts.begin();
        return false;
    }

    const NodePtr Next() {
        if(this->nextIt == this->nexts.end())
            this->nextIt = this->nexts.begin();
        return *(this->nextIt++);
    }

    void SetNextNodesExceptSelf(const Nodes& nodes) {
        this->nexts.reserve(nodes.size());
        this->nexts.assign(nodes.begin(), nodes.end());
        this->nextIt = this->nexts.begin();

        for(auto nextIt=this->nexts.begin(); nextIt!=this->nexts.end(); ++nextIt)
            if((*nextIt)->id == this->id) {
                this->nexts.erase(nextIt);
                return;
            }
    }
};


// 모든 노드들의 완전 연결 그래프 생성
class CompleteGraph {
public: 
    static Nodes& Make(const ID_T& nodeNum, Nodes* nodes) {
        nodes->reserve(nodeNum);

        for(ID_T i=0; i!=nodeNum; ++i)
            nodes->emplace_back(new Node(i));

        for(auto& node : *nodes)
            node->SetNextNodesExceptSelf(*nodes);
    }
};


/* 중복되는 경로를 고려하지 않고 완전 모든 경로를 탐색하는 DFS */
class DFSCostCalculator {
private:
    int nodeNum, visitedNum;
    std::vector<double>* costs;

public:
    DFSCostCalculator(int nodeNum) : nodeNum(nodeNum), visitedNum(0) {}

    double MinTravelCost(const Nodes& nodes) {
        /* 탐색하면서 구한 cost를 모두 저장하는 벡터 */
        std::vector<double> allCosts;
        this->costs = &allCosts;

        /* 각 노드로부터 출발하는 모든 경로 탐색 */
        for(auto& node: nodes)
            this->calcTravelCostFrom(node, 0);

        /* 탐색하며 구한 costs중 최소값 */
        return findMinFromAllCosts(allCosts);
    }

private: 
    /* costmap으로 부터 얻은 cost를 두번째 인자에 누적하며 DF 탐색*/
    void calcTravelCostFrom(const NodePtr& curr, double cost) {
        curr->isVisit = true;
        this->visitedNum += 1;

        if(this->visitedNum == this->nodeNum)/*각 노드를 전부 거친 상태일때*/
            this->costs->push_back(cost);/*누적한 cost를 벡터에 push*/

        while(curr->HasNext()) {
            auto next = curr->Next();

            if(next->isVisit)
                continue;

            this->calcTravelCostFrom(
                next,
                cost + CostMap::Get(curr->id, next->id)
            );
        }

        curr->isVisit = false;
        this->visitedNum -= 1;
    }

    double findMinFromAllCosts(std::vector<double>& allCosts) {
        double min = allCosts[0];
        for(auto& cost : allCosts)
            if(min > cost)
                min = cost;

        return min;
    }
};


double solution(const int& numCity, const std::vector<std::vector<double>>& costs) {
    CostMap::Reset(costs);

    auto graph = Nodes();
    CompleteGraph::Make(numCity, &graph);

    auto calculator = DFSCostCalculator(numCity);
    return calculator.MinTravelCost(graph);
}

#ifndef TEST_BUILD
void parseAndSetCosts(const int& numCity, std::vector<std::vector<double>>* costs);
int main(int argc, const char *argv[])
{
    std::cout<<std::fixed;
    std::cout.precision(10);

    int numTest;
    for(std::cin>>numTest; numTest!=0; --numTest) {
        int numCity;
        std::cin >> numCity;

        auto costs = std::vector<std::vector<double>>(numCity, std::vector<double>(numCity));
        parseAndSetCosts(numCity, &costs);

        std::cout<< solution(numCity, costs) <<std::endl;
    }
}

void parseAndSetCosts(const int& numCity, std::vector<std::vector<double>>* costs) {
    for(int i=0; i!=numCity; ++i)
        for(int j=0; j!=numCity; ++j)
            std::cin >> (*costs)[i][j];
}
#endif
