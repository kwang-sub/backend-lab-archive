"use client"

import Link from "next/link"
import { useMemo, useState } from "react"
import { ThumbsDown, ThumbsUp } from "lucide-react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import {
  applyCityFilters,
  BEST_SEASON_OPTIONS,
  BUDGET_OPTIONS,
  ENVIRONMENT_OPTIONS,
  INITIAL_CITY_DATA,
  REGION_OPTIONS,
  sortCities,
  toggleVote,
  UNSELECTED_FILTER_VALUE,
  type BestSeasonOption,
  type BudgetOption,
  type CityData,
  type EnvironmentOption,
  type FilterState,
  type RegionOption,
  type VoteType,
} from "@/lib/city-ranking"
import { cn } from "@/lib/utils"

function voteButtonClass(type: VoteType, active: boolean) {
  const baseClass =
    "inline-flex items-center gap-2 rounded-md border px-3 py-2 text-sm font-medium transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-cyan-300"
  const inactiveClass = "border-slate-700 bg-slate-800/60 text-slate-300 hover:bg-slate-700/80"

  if (!active) {
    return cn(baseClass, inactiveClass)
  }

  if (type === "like") {
    return cn(baseClass, "border-emerald-400/40 bg-emerald-500/15 text-emerald-300 hover:bg-emerald-500/20")
  }

  return cn(baseClass, "border-rose-400/40 bg-rose-500/15 text-rose-300 hover:bg-rose-500/20")
}

type CityRankingBoardProps = {
  initialCities?: CityData[]
}

export default function Component({ initialCities = INITIAL_CITY_DATA }: CityRankingBoardProps) {
  const [cities, setCities] = useState<CityData[]>(initialCities)
  const [filters, setFilters] = useState<FilterState>({
    region: "전체",
  })
  const filteredCities = useMemo(() => sortCities(applyCityFilters(cities, filters)), [cities, filters])

  return (
    <div className="min-h-screen bg-[#050816] px-4 py-6 font-['Pretendard','Inter','system-ui',sans-serif] text-slate-100">
      <div className="pointer-events-none absolute inset-0 bg-[radial-gradient(circle_at_20%_10%,rgba(56,189,248,0.14),transparent_40%),radial-gradient(circle_at_80%_0%,rgba(45,212,191,0.12),transparent_35%),radial-gradient(circle_at_50%_100%,rgba(14,165,233,0.12),transparent_45%)]" />
      <div className="relative mx-auto max-w-6xl">
        <Card className="border-slate-700/60 bg-slate-900/70 shadow-[0_20px_90px_rgba(2,6,23,0.7)] backdrop-blur">
          <CardHeader className="space-y-2 border-b border-slate-700/60">
            <p className="text-xs uppercase tracking-[0.2em] text-cyan-300/80">City Discovery Board</p>
            <CardTitle className="text-3xl font-semibold text-slate-50">도시 리스트</CardTitle>
            <p className="text-sm text-slate-300">필터 조건과 선호 투표를 기반으로 도시 후보를 빠르게 비교하세요.</p>
          </CardHeader>
          <CardContent className="space-y-5 p-6">
            <section className="grid gap-3 rounded-xl border border-slate-700/70 bg-slate-950/70 p-4 md:grid-cols-2">
              <div className="space-y-1">
                <Label htmlFor="budget-filter" className="text-slate-200">
                  예산
                </Label>
                <Select
                  value={filters.budget ?? UNSELECTED_FILTER_VALUE}
                  onValueChange={(value) =>
                    setFilters((prev) => ({
                      ...prev,
                      budget: value === UNSELECTED_FILTER_VALUE ? undefined : (value as BudgetOption),
                    }))
                  }
                >
                  <SelectTrigger
                    id="budget-filter"
                    className="border-slate-500/80 bg-slate-800/90 text-slate-50 data-[placeholder]:text-slate-400 focus:ring-2 focus:ring-cyan-300 focus:ring-offset-0"
                  >
                    <SelectValue placeholder="선택 안 함" />
                  </SelectTrigger>
                  <SelectContent className="border-slate-600 bg-slate-900 text-slate-100 shadow-xl shadow-slate-950/80">
                    <SelectItem className="focus:bg-cyan-500/20 focus:text-cyan-100" value={UNSELECTED_FILTER_VALUE}>
                      선택 안 함
                    </SelectItem>
                    {BUDGET_OPTIONS.map((option) => (
                      <SelectItem key={option} className="focus:bg-cyan-500/20 focus:text-cyan-100" value={option}>
                        {option}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div className="space-y-1">
                <Label htmlFor="region-filter" className="text-slate-200">
                  지역
                </Label>
                <Select
                  value={filters.region ?? "전체"}
                  onValueChange={(value) =>
                    setFilters((prev) => ({
                      ...prev,
                      region: value as RegionOption,
                    }))
                  }
                >
                  <SelectTrigger
                    id="region-filter"
                    className="border-slate-500/80 bg-slate-800/90 text-slate-50 data-[placeholder]:text-slate-400 focus:ring-2 focus:ring-cyan-300 focus:ring-offset-0"
                  >
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent className="border-slate-600 bg-slate-900 text-slate-100 shadow-xl shadow-slate-950/80">
                    {REGION_OPTIONS.map((option) => (
                      <SelectItem key={option} className="focus:bg-cyan-500/20 focus:text-cyan-100" value={option}>
                        {option}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div className="space-y-1">
                <Label htmlFor="environment-filter" className="text-slate-200">
                  환경
                </Label>
                <Select
                  value={filters.environment ?? UNSELECTED_FILTER_VALUE}
                  onValueChange={(value) =>
                    setFilters((prev) => ({
                      ...prev,
                      environment: value === UNSELECTED_FILTER_VALUE ? undefined : (value as EnvironmentOption),
                    }))
                  }
                >
                  <SelectTrigger
                    id="environment-filter"
                    className="border-slate-500/80 bg-slate-800/90 text-slate-50 data-[placeholder]:text-slate-400 focus:ring-2 focus:ring-cyan-300 focus:ring-offset-0"
                  >
                    <SelectValue placeholder="선택 안 함" />
                  </SelectTrigger>
                  <SelectContent className="border-slate-600 bg-slate-900 text-slate-100 shadow-xl shadow-slate-950/80">
                    <SelectItem className="focus:bg-cyan-500/20 focus:text-cyan-100" value={UNSELECTED_FILTER_VALUE}>
                      선택 안 함
                    </SelectItem>
                    {ENVIRONMENT_OPTIONS.map((option) => (
                      <SelectItem key={option} className="focus:bg-cyan-500/20 focus:text-cyan-100" value={option}>
                        {option}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div className="space-y-1">
                <Label htmlFor="season-filter" className="text-slate-200">
                  최고 계절
                </Label>
                <Select
                  value={filters.bestSeason ?? UNSELECTED_FILTER_VALUE}
                  onValueChange={(value) =>
                    setFilters((prev) => ({
                      ...prev,
                      bestSeason: value === UNSELECTED_FILTER_VALUE ? undefined : (value as BestSeasonOption),
                    }))
                  }
                >
                  <SelectTrigger
                    id="season-filter"
                    className="border-slate-500/80 bg-slate-800/90 text-slate-50 data-[placeholder]:text-slate-400 focus:ring-2 focus:ring-cyan-300 focus:ring-offset-0"
                  >
                    <SelectValue placeholder="선택 안 함" />
                  </SelectTrigger>
                  <SelectContent className="border-slate-600 bg-slate-900 text-slate-100 shadow-xl shadow-slate-950/80">
                    <SelectItem className="focus:bg-cyan-500/20 focus:text-cyan-100" value={UNSELECTED_FILTER_VALUE}>
                      선택 안 함
                    </SelectItem>
                    {BEST_SEASON_OPTIONS.map((option) => (
                      <SelectItem key={option} className="focus:bg-cyan-500/20 focus:text-cyan-100" value={option}>
                        {option}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </section>
            {filteredCities.map((city) => (
              <article
                key={city.id}
                data-testid="city-card"
                className="space-y-3 rounded-xl border border-slate-700/70 bg-slate-900/65 p-4 shadow-[0_10px_35px_rgba(15,23,42,0.55)]"
              >
                <div className="flex items-start justify-between gap-4">
                  <div className="space-y-1">
                    <h2 className="text-lg font-semibold text-slate-50">{city.name}</h2>
                    {city.description ? <p className="text-sm text-slate-300">{city.description}</p> : null}
                  </div>
                  <Link
                    href={`/cities/${city.id}`}
                    className="shrink-0 rounded-md border border-cyan-400/40 bg-cyan-400/10 px-3 py-2 text-sm font-medium text-cyan-200 transition-colors hover:bg-cyan-400/20 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-cyan-300"
                  >
                    상세 보기
                  </Link>
                </div>
                <dl className="grid gap-2 text-sm md:grid-cols-2">
                  <div className="rounded-lg border border-slate-700 bg-slate-950/80 p-2">
                    <dt className="text-xs text-slate-400">예산</dt>
                    <dd className="mt-1 text-slate-100">{city.budget}</dd>
                  </div>
                  <div className="rounded-lg border border-slate-700 bg-slate-950/80 p-2">
                    <dt className="text-xs text-slate-400">지역</dt>
                    <dd className="mt-1 text-slate-100">{city.region}</dd>
                  </div>
                  <div className="rounded-lg border border-slate-700 bg-slate-950/80 p-2">
                    <dt className="text-xs text-slate-400">환경</dt>
                    <dd className="mt-1 text-slate-100">{city.environment}</dd>
                  </div>
                  <div className="rounded-lg border border-slate-700 bg-slate-950/80 p-2">
                    <dt className="text-xs text-slate-400">최고 계절</dt>
                    <dd className="mt-1 text-slate-100">{city.bestSeason}</dd>
                  </div>
                </dl>
                <div className="flex items-center gap-2">
                  <button
                    type="button"
                    className={voteButtonClass("like", city.userVote.like)}
                    onClick={() => setCities((prev) => toggleVote(prev, city.id, "like"))}
                  >
                    <ThumbsUp className="h-4 w-4" />
                    좋아요 {city.likes}
                  </button>
                  <button
                    type="button"
                    className={voteButtonClass("dislike", city.userVote.dislike)}
                    onClick={() => setCities((prev) => toggleVote(prev, city.id, "dislike"))}
                  >
                    <ThumbsDown className="h-4 w-4" />
                    싫어요 {city.dislikes}
                  </button>
                </div>
              </article>
            ))}
            {filteredCities.length === 0 && (
              <p className="rounded-lg border border-dashed border-slate-600 p-4 text-sm text-slate-300">
                조건에 맞는 도시가 없습니다.
              </p>
            )}
          </CardContent>
        </Card>
      </div>
    </div>
  )
}
