export type BudgetOption = "100만원" | "100~200만원" | "200만원"
export type RegionOption = "전체" | "수도권" | "경상도" | "전라도" | "강원도" | "제주도" | "충청도"
export type EnvironmentOption = "자연친화" | "도심선호" | "카페작업" | "코워킹 필수"
export type BestSeasonOption = "봄" | "여름" | "가을" | "겨울"
export type VoteType = "like" | "dislike"

export type VoteState = {
  like: boolean
  dislike: boolean
}

export interface CityData {
  id: string
  name: string
  description?: string
  detailedDescription: string
  highlights: string[]
  budget: BudgetOption
  region: RegionOption
  environment: EnvironmentOption
  bestSeason: BestSeasonOption
  likes: number
  dislikes: number
  userVote: VoteState
}

export type FilterState = {
  budget?: BudgetOption
  region?: RegionOption
  environment?: EnvironmentOption
  bestSeason?: BestSeasonOption
}

export const BUDGET_OPTIONS: BudgetOption[] = ["100만원", "100~200만원", "200만원"]
export const REGION_OPTIONS: RegionOption[] = ["전체", "수도권", "경상도", "전라도", "강원도", "제주도", "충청도"]
export const ENVIRONMENT_OPTIONS: EnvironmentOption[] = ["자연친화", "도심선호", "카페작업", "코워킹 필수"]
export const BEST_SEASON_OPTIONS: BestSeasonOption[] = ["봄", "여름", "가을", "겨울"]
export const UNSELECTED_FILTER_VALUE = "__none__"

export const INITIAL_CITY_DATA: CityData[] = [
  {
    id: "seoul",
    name: "서울",
    description: "도심 생활 인프라와 교통 접근성이 뛰어난 도시",
    detailedDescription:
      "서울은 대중교통과 생활 편의시설이 밀집해 있어 빠른 적응이 가능하며, 다양한 문화 공간과 업무 거점을 활용하기 좋습니다.",
    highlights: ["촘촘한 대중교통", "다양한 업무 지구", "풍부한 문화 생활"],
    budget: "200만원",
    region: "수도권",
    environment: "도심선호",
    bestSeason: "가을",
    likes: 0,
    dislikes: 0,
    userVote: { like: false, dislike: false },
  },
  {
    id: "busan",
    name: "부산",
    description: "바다와 도심을 함께 누릴 수 있는 해안 도시",
    detailedDescription:
      "부산은 바다 접근성과 도심 인프라를 동시에 누릴 수 있어 워케이션과 장기 체류에 모두 적합한 해안형 거점입니다.",
    highlights: ["해변 접근성", "균형 잡힌 생활비", "활발한 카페 문화"],
    budget: "100~200만원",
    region: "경상도",
    environment: "카페작업",
    bestSeason: "여름",
    likes: 0,
    dislikes: 0,
    userVote: { like: false, dislike: false },
  },
  {
    id: "gangneung",
    name: "강릉",
    description: "자연친화적 라이프스타일과 여유로운 분위기의 도시",
    detailedDescription:
      "강릉은 바다와 숲을 가까이 두고 비교적 느린 템포로 생활할 수 있어 자연 중심의 정주 환경을 선호하는 사용자에게 적합합니다.",
    highlights: ["해변과 산책 코스", "여유로운 생활 리듬", "자연 친화 워케이션"],
    budget: "100만원",
    region: "강원도",
    environment: "자연친화",
    bestSeason: "봄",
    likes: 87,
    dislikes: 8,
    userVote: { like: false, dislike: false },
  },
  {
    id: "jeonju",
    name: "전주",
    description: "한옥마을과 지역 문화가 살아 있는 도시",
    detailedDescription:
      "전주는 지역 문화와 음식, 한옥 기반의 관광 자원이 풍부해 느긋한 생활과 개성 있는 지역 경험을 동시에 누릴 수 있습니다.",
    highlights: ["풍부한 지역 문화", "강한 음식 정체성", "개성 있는 카페 거리"],
    budget: "100만원",
    region: "전라도",
    environment: "카페작업",
    bestSeason: "가을",
    likes: 76,
    dislikes: 7,
    userVote: { like: false, dislike: false },
  },
  {
    id: "jeju",
    name: "제주",
    description: "자연경관과 원격 근무 환경을 동시에 누릴 수 있는 섬 도시",
    detailedDescription:
      "제주는 자연환경과 원격 근무 친화 공간이 함께 발달해 있어 장기 체류형 노마드 라이프를 계획하는 사용자에게 매력적인 선택지입니다.",
    highlights: ["탁월한 자연 경관", "원격 근무 수요", "계절별 체류 경험"],
    budget: "200만원",
    region: "제주도",
    environment: "코워킹 필수",
    bestSeason: "겨울",
    likes: 90,
    dislikes: 15,
    userVote: { like: false, dislike: false },
  },
  {
    id: "daejeon",
    name: "대전",
    description: "교통 중심지이면서 안정적인 정주 환경을 갖춘 도시",
    detailedDescription:
      "대전은 전국 이동이 편리하고 생활 인프라가 안정적이어서 장기 거주와 이동 편의성을 함께 고려하는 사용자에게 유리합니다.",
    highlights: ["전국 단위 교통 접근성", "안정적인 정주 환경", "실용적인 생활비"],
    budget: "100~200만원",
    region: "충청도",
    environment: "도심선호",
    bestSeason: "봄",
    likes: 74,
    dislikes: 9,
    userVote: { like: false, dislike: false },
  },
]

export function applyCityFilters(cities: CityData[], filters: FilterState): CityData[] {
  return cities.filter((city) => {
    const matchesBudget = !filters.budget || city.budget === filters.budget
    const matchesRegion = !filters.region || filters.region === "전체" || city.region === filters.region
    const matchesEnvironment = !filters.environment || city.environment === filters.environment
    const matchesBestSeason = !filters.bestSeason || city.bestSeason === filters.bestSeason

    return matchesBudget && matchesRegion && matchesEnvironment && matchesBestSeason
  })
}

export function getCityById(cityId: string): CityData | undefined {
  return INITIAL_CITY_DATA.find((city) => city.id === cityId)
}

export function sortCities(cities: CityData[]): CityData[] {
  return [...cities].sort((left, right) => {
    if (right.likes !== left.likes) {
      return right.likes - left.likes
    }

    return left.name.localeCompare(right.name, "ko")
  })
}

export function toggleVote(cities: CityData[], cityId: string, voteType: VoteType): CityData[] {
  return cities.map((city) => {
    if (city.id !== cityId) {
      return city
    }

    if (voteType === "like") {
      const isActive = city.userVote.like
      return {
        ...city,
        likes: city.likes + (isActive ? -1 : 1),
        userVote: {
          ...city.userVote,
          like: !isActive,
        },
      }
    }

    const isActive = city.userVote.dislike
    return {
      ...city,
      dislikes: city.dislikes + (isActive ? -1 : 1),
      userVote: {
        ...city.userVote,
        dislike: !isActive,
      },
    }
  })
}
