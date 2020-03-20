/**********************************************************************
작성일 : 2020-01-16
작성자 : 명근
문제 : 가사 검색
출처 : 프로그래머스
풀이 : 모든 경우 순회
예상 시간복잡도 : 쿼리의 갯수 N, word의 갯수 M, 각문자열의 최대길이 L이라고 할 때 
                  O(N*M*L) => N~=M < 100,000, L < 10,000, => O(N^2 * L)
TEST 결과 : 
 프로그래머스-효율성 2번 틀림 ㄷ;
**********************************************************************/
"use strict";
function solution(words, queries) {
  let queryMatchCounts = new Array(queries.length);
  queryMatchCounts.fill(0);

  let evaledQueries = {};

  queries.forEach((query, idx)=>{
    if(evaledQueries.hasOwnProperty(query)) {
      queryMatchCounts[idx] = evaledQueries[query];
      return;
    }

    let queryLen = query.length;
    words.forEach(word=>{
      if(word.length === queryLen) {
        if(isQueryMatch({word, query}))
          queryMatchCounts[idx] += 1;
        else
          return;
      }
    });
    evaledQueries[query] = queryMatchCounts[idx];
  });

  return queryMatchCounts;
}

function isQueryMatch(arg) {
  let word = arg.word, query = arg.query;

  if(query[0] === '?' && query[query.length-1] === '?')
    return true;

  if(query[0] === '?') 
    return isMatchZtoA(word, query);
  else /* query[len-1] === '?' */
    return isMatchAtoZ(word, query);

}

function isMatchZtoA(word, query) {
  for(let i=query.length-1; i>=0; --i) {
    if(query[i] === '?') 
      return true;

    if(word[i] !== query[i]) 
      return false;
  }
}

function isMatchAtoZ(word, query) {
  for(let i=0; i!=query.length; ++i) {
    if(query[i] === '?') 
      return true;

    if(word[i] !== query[i]) 
      return false;
  }
}

module.exports = {solution, isQueryMatch};
