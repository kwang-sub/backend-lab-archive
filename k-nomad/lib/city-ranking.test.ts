import { describe, expect, it } from "@jest/globals"
import { getCityById } from "@/lib/city-ranking"

describe("getCityById", () => {
  it("도시 id로 상세 데이터를 조회한다", () => {
    const city = getCityById("seoul")

    expect(city).toBeDefined()
    expect(city?.name).toBe("서울")
    expect(city?.detailedDescription).toContain("대중교통")
    expect(city?.highlights.length).toBeGreaterThan(0)
  })

  it("존재하지 않는 id는 undefined를 반환한다", () => {
    expect(getCityById("unknown-city")).toBeUndefined()
  })
})
