import Link from "next/link"
import { notFound } from "next/navigation"
import { ArrowLeft, MapPin, Wallet } from "lucide-react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { getCityById } from "@/lib/city-ranking"

type CityDetailPageProps = {
  params: Promise<{
    slug: string
  }>
}

function detailCardClassName() {
  return "rounded-xl border border-slate-800 bg-slate-900/80 p-4"
}

export default async function CityDetailPage({ params }: CityDetailPageProps) {
  const { slug } = await params
  const city = getCityById(slug)

  if (!city) {
    notFound()
  }

  return (
    <main className="min-h-screen bg-[#050816] px-4 py-10 font-['Pretendard','Inter','system-ui',sans-serif] text-slate-100">
      <div className="mx-auto max-w-5xl space-y-6">
        <Link
          href="/"
          className="inline-flex items-center gap-2 text-sm font-medium text-cyan-200 transition-colors hover:text-cyan-100"
        >
          <ArrowLeft className="h-4 w-4" />
          홈으로 돌아가기
        </Link>

        <Card className="overflow-hidden border-slate-800 bg-slate-950/85 shadow-[0_18px_60px_rgba(2,6,23,0.55)]">
          <CardHeader className="space-y-4 border-b border-slate-800 bg-[radial-gradient(circle_at_top_left,rgba(34,211,238,0.18),transparent_35%),linear-gradient(180deg,rgba(15,23,42,0.88),rgba(2,6,23,0.94))]">
            <div className="space-y-2">
              <p className="text-xs uppercase tracking-[0.24em] text-cyan-300/80">City Detail</p>
              <CardTitle className="text-3xl font-semibold text-white">{city.name}</CardTitle>
              <p className="max-w-3xl text-sm leading-6 text-slate-300">{city.detailedDescription}</p>
            </div>
            <div className="flex flex-wrap gap-3 text-sm text-slate-200">
              <div className="inline-flex items-center gap-2 rounded-full border border-slate-700 bg-slate-900/70 px-3 py-1.5">
                <MapPin className="h-4 w-4 text-cyan-300" />
                {city.region}
              </div>
              <div className="inline-flex items-center gap-2 rounded-full border border-slate-700 bg-slate-900/70 px-3 py-1.5">
                <Wallet className="h-4 w-4 text-cyan-300" />
                {city.budget}
              </div>
              <div className="rounded-full border border-slate-700 bg-slate-900/70 px-3 py-1.5">{city.environment}</div>
              <div className="rounded-full border border-slate-700 bg-slate-900/70 px-3 py-1.5">
                추천 계절 {city.bestSeason}
              </div>
            </div>
          </CardHeader>

          <CardContent className="grid gap-4 p-6 md:grid-cols-[minmax(0,2fr)_minmax(280px,1fr)]">
            <section className="space-y-4">
              <div className={detailCardClassName()}>
                <h2 className="text-sm font-semibold uppercase tracking-[0.18em] text-cyan-300/80">핵심 요약</h2>
                <p className="mt-3 text-base leading-7 text-slate-200">{city.description}</p>
              </div>

              <div className={detailCardClassName()}>
                <h2 className="text-sm font-semibold uppercase tracking-[0.18em] text-cyan-300/80">추천 포인트</h2>
                <ul className="mt-3 grid gap-3 text-sm text-slate-200">
                  {city.highlights.map((highlight) => (
                    <li key={highlight} className="rounded-lg border border-slate-800 bg-slate-950/80 px-3 py-3">
                      {highlight}
                    </li>
                  ))}
                </ul>
              </div>
            </section>

            <aside className="space-y-4">
              <div className={detailCardClassName()}>
                <h2 className="text-sm font-semibold uppercase tracking-[0.18em] text-cyan-300/80">생활 지표</h2>
                <dl className="mt-4 space-y-3 text-sm">
                  <div className="flex items-center justify-between gap-4">
                    <dt className="text-slate-400">예산</dt>
                    <dd className="font-medium text-slate-100">{city.budget}</dd>
                  </div>
                  <div className="flex items-center justify-between gap-4">
                    <dt className="text-slate-400">지역</dt>
                    <dd className="font-medium text-slate-100">{city.region}</dd>
                  </div>
                  <div className="flex items-center justify-between gap-4">
                    <dt className="text-slate-400">환경</dt>
                    <dd className="font-medium text-slate-100">{city.environment}</dd>
                  </div>
                  <div className="flex items-center justify-between gap-4">
                    <dt className="text-slate-400">추천 계절</dt>
                    <dd className="font-medium text-slate-100">{city.bestSeason}</dd>
                  </div>
                </dl>
              </div>

              <div className={detailCardClassName()}>
                <h2 className="text-sm font-semibold uppercase tracking-[0.18em] text-cyan-300/80">선호 반응</h2>
                <div className="mt-4 grid grid-cols-2 gap-3">
                  <div className="rounded-lg border border-emerald-500/30 bg-emerald-500/10 px-4 py-4">
                    <p className="text-xs uppercase tracking-[0.16em] text-emerald-200/80">좋아요</p>
                    <p className="mt-2 text-2xl font-semibold text-emerald-100">{city.likes}</p>
                  </div>
                  <div className="rounded-lg border border-rose-500/30 bg-rose-500/10 px-4 py-4">
                    <p className="text-xs uppercase tracking-[0.16em] text-rose-200/80">싫어요</p>
                    <p className="mt-2 text-2xl font-semibold text-rose-100">{city.dislikes}</p>
                  </div>
                </div>
              </div>
            </aside>
          </CardContent>
        </Card>
      </div>
    </main>
  )
}
