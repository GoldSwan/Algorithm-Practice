/**********************************************************************
작성일 : 2020-01-16
작성자 : 명근
문제 : 가사 검색
출처 : 프로그래머스
풀이 : 
--- 1 ---
    1. words를 trie로 만든다. 
    2. wc(wild card)-first인 쿼리는 wc 갯수만큼의 층으로 bfs로 들어간다음 
       문자 검색해서 dfs로 마저 탐색하고 카운트를 올린다.
    3. wc(wild card)-last인 쿼리는 트리에서 문자를 검색해서 들어간다음 
       depth가 wc 수와 같은 노드에서만 자식 노드 수를 카운트에 더한다.

--- 2 ---
    1. 1과 같이 하되 trie을 정순, 역순 두 개 만든다. 
    2. wc(wild card)-first인 쿼리는 역순 trie로 탐색하고 자식노드 갯수를 센다.
    3. wc(wild card)-last인 쿼리는 정순 trie로 탐색하고 자식노드 갯수를 센다.

--- 3 ---
    1. 1과 같이 하되 쿼리가 중복될 경우 이전에 평가된 값을 사용하게 한다.

예상 시간복잡도 : 
    trie의 높이는 가장 긴 문자열의 길이와 같다. :=L이라고 할 때 탐색시간은 O(L)이다.
    2번은 wc가 아닐때까지 모든 노드 탐색해야하므로 wc가 아주 클 때 O(N)이 된다.
    3번은 wc를 만날때까지 탐색하므로 wc가 아주 작을 때, O(L)이라고 할 수 있다.
    2번 때문에 words의 수를 N, queries의 수를 M이라고하면 O(N*M)이다.
    메모리 제한이 없는거 같으니까 words들을 거꾸로 거스르는 trie를 하나 더 만들면
    O(M*L)이 된다.

TEST 결과 : 
    1. 프로그래머스 효율성 다 떨어짐
    2. 프로그래머스 효율성 5 통과
    3. 프로그래머스 통과
**********************************************************************/
#include <string>
#include <vector>
#include <unordered_map>
#include <memory>

class Trie;
using TriePtr = Trie*;
using TrieMap = std::unordered_map<char, TriePtr>;
using StringVector = std::vector<std::string>;

inline bool isWildCard(const char c) { return c == '?'; }
inline bool isNullChar(const char c) { return c == '\0'; }
inline const char* firstOf(const std::string& str) { return &str[0]; }
inline const char* lastOf(const std::string& str) { return &str[str.size()-1]; }

/* 해시 이용한 Trie 구현, 역순 Trie도 추가하기 위해 
 * '시작 노드'와 '다음 노드'를 추상메소드로 나타냄*/
class Trie {
protected:
    bool last;
    TrieMap nexts;

public:
    Trie() : last(false) {
        this->nexts.reserve(26); /* num of alphabets */
    }

    ~Trie() {
        for(auto& next : this->nexts)
            delete(next.second);

        this->nexts.clear();
    }

    int MatchCount(const std::string& query) {
        return this->calcMatchCountAt(this->firstKey(query));
    }

    static TriePtr MakeTrie(const StringVector& strings) {
        TriePtr header = new Trie();
        
        for(auto& string: strings)
            header->insert(firstOf(string));

        return header;
    }

protected:
    void insert(const char* key) {
        if(isNullChar(*key)) {
            this->last = true;
            return;
        }

        if(this->hasNoChild(*key))
            this->addChild(*key);

        this->nexts[*key]->insert(this->nextKey(key));
    }

    int calcMatchCountAt(const char* key) {
        if(isNullChar(*key) && this->last)
            return 1;

        if(isWildCard(*key))
            return this->calcMatchCountAtAll(key);

        if(this->hasNoChild(*key))
            return 0;

        return this->nexts[*key]->calcMatchCountAt(this->nextKey(key));
    }

    int calcMatchCountAtAll(const char* key) {
        int ret = 0;

        for(auto next : this->nexts)
            ret += next.second->calcMatchCountAt(this->nextKey(key));

        return ret;
    }

    inline bool hasNoChild(const char& key) {
        return this->nexts.find(key) == this->nexts.end();
    }

    virtual const char* firstKey(const std::string& string) {
        return firstOf(string);
    }

    virtual const char* nextKey(const char* key) {
        return key+1;
    }

    virtual void addChild(const char key) {
        this->nexts.emplace(key, new Trie());
    }
};

/*주어진 문자열을 역순으로 검색하는 Trie*/
class ReverseTrie : public Trie {
public:
    static TriePtr MakeTrie(const StringVector& strings) {
        ReverseTrie* header = new ReverseTrie();

        for(auto& string: strings)
            header->insert(lastOf(string));

        return (TriePtr)header;
    }

protected:
    virtual const char* firstKey(const std::string& string) override final {
        return lastOf(string);
    }

    virtual const char* nextKey(const char* key) override final {
        return key-1;
    }

    virtual void addChild(const char key) override final {
        this->nexts.emplace(key, new ReverseTrie());
    }
};


std::vector<int> solution(StringVector words, StringVector queries) {
    TriePtr wordTrie = Trie::MakeTrie(words);
    TriePtr wordTrieReverse = ReverseTrie::MakeTrie(words);

    int numQueries = queries.size();
    auto matchCounts = std::vector<int>(numQueries);
    auto evaledMatchCounts = std::unordered_map<std::string, int>(numQueries);

    for(int i=0; i!=numQueries; ++i) {
        auto& query = queries[i];

        /*this query evaluated before*/
        if(evaledMatchCounts.find(query) != evaledMatchCounts.end()) {
            matchCounts[i] = evaledMatchCounts[query];
            continue;
        }
        
        /* wildcard가 앞에 있는 경우 문자열의 뒤에서부터,
         * wildcard가 뒤에 있는 경우 문자열의 앞에서부터 검색*/
        matchCounts[i] = evaledMatchCounts[query] = isWildCard(query[0])?
            wordTrieReverse->MatchCount(query) : wordTrie->MatchCount(query);
    }

    return matchCounts;
}
