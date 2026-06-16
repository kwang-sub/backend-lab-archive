import assert from "node:assert/strict"
import { readFileSync } from "node:fs"
import test from "node:test"

import { applyCityFilters, INITIAL_CITY_DATA, sortCities, toggleVote, type CityData } from "./lib/city-ranking"

function createCity(overrides: Partial<CityData>): CityData {
  return {
    id: "city",
    name: "Alpha",
    description: "test city",
    detailedDescription: "test detail",
    highlights: ["highlight"],
    budget: "100만원",
    region: "수도권",
    environment: "도심선호",
    bestSeason: "봄",
    likes: 1,
    dislikes: 0,
    userVote: { like: false, dislike: false },
    ...overrides,
  }
}

test("필터를 단일 및 복합 조건으로 적용한다", () => {
  const singleFilter = applyCityFilters(INITIAL_CITY_DATA, { region: "제주도" })
  const combinedFilter = applyCityFilters(INITIAL_CITY_DATA, {
    budget: "200만원",
    region: "제주도",
    environment: "코워킹 필수",
    bestSeason: "겨울",
  })

  assert.deepEqual(singleFilter.map((city) => city.name), ["제주"])
  assert.deepEqual(combinedFilter.map((city) => city.name), ["제주"])
})

test("지역이 전체면 지역 필터를 적용하지 않는다", () => {
  const allCities = applyCityFilters(INITIAL_CITY_DATA, { region: "전체" })

  assert.equal(allCities.length, INITIAL_CITY_DATA.length)
})

test("좋아요와 싫어요는 독립적으로 토글된다", () => {
  const baseCities = [createCity({ id: "alpha", likes: 3, dislikes: 1 })]
  const liked = toggleVote(baseCities, "alpha", "like")
  const likedAndDisliked = toggleVote(liked, "alpha", "dislike")
  const unliked = toggleVote(likedAndDisliked, "alpha", "like")

  assert.deepEqual(likedAndDisliked[0], {
    ...baseCities[0],
    likes: 4,
    dislikes: 2,
    userVote: { like: true, dislike: true },
  })
  assert.deepEqual(unliked[0], {
    ...baseCities[0],
    likes: 3,
    dislikes: 2,
    userVote: { like: false, dislike: true },
  })
})

test("좋아요 토글 후 정렬이 즉시 다시 적용된다", () => {
  const cities = [
    createCity({ id: "beta", name: "Beta", likes: 2 }),
    createCity({ id: "alpha", name: "Alpha", likes: 1 }),
  ]

  const reordered = sortCities(toggleVote(cities, "alpha", "like"))

  assert.deepEqual(reordered.map((city) => city.name), ["Alpha", "Beta"])
})

test("좋아요 수 내림차순, 동점 시 이름 오름차순으로 정렬한다", () => {
  const sorted = sortCities([
    createCity({ id: "beta", name: "Beta", likes: 2 }),
    createCity({ id: "alpha", name: "Alpha", likes: 2 }),
    createCity({ id: "gamma", name: "Gamma", likes: 5 }),
  ])

  assert.deepEqual(sorted.map((city) => city.name), ["Gamma", "Alpha", "Beta"])
})

test("제거 대상 UI 문자열이 컴포넌트 소스에 존재하지 않는다", () => {
  const source = readFileSync("./crypto-ranking-board.tsx", "utf8")

  assert.equal(source.includes("자세히 보기"), false)
  assert.equal(source.includes("별점"), false)
  assert.equal(source.includes("평점"), false)
  assert.equal(source.includes("회원가입"), false)
  assert.equal(source.includes("로그인"), false)
})
