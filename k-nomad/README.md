# Crypto Ranking Board

암호화폐 시세와 순위를 한눈에 볼 수 있는 Next.js 15 기반 대시보드입니다. 현재는 목업 데이터를 사용하지만 CoinAPI 연동을 통해 실시간 데이터를 표시하도록 확장할 수 있도록 구조화되어 있습니다.

## 주요 특징
- React 19 + Next.js App Router 구조
- shadcn/ui와 Tailwind CSS를 이용한 다크 테마 UI
- Lucide 아이콘과 반응형 표 레이아웃
- 추후 CoinAPI 연동을 고려한 데이터 포맷 정의(`CoinData` 인터페이스)

## 기술 스택
- 프런트엔드: Next.js 15.2.4, React 19, TypeScript
- UI & 스타일: Tailwind CSS, shadcn/ui, Radix UI 프리미티브
- 상태/폼: react-hook-form, zod
- 기타: lucide-react, recharts (향후 시각화용), Jest + Testing Library

## 사전 준비
1. Node.js 20 이상
2. 패키지 매니저: 프로젝트는 `pnpm` 9.x를 기준으로 설정됨 (`packageManager` 필드 참고)

## 설치 및 실행
```bash
pnpm install      # 의존성 설치
pnpm dev          # 개발 서버 (http://localhost:3000)
pnpm build        # 프로덕션 번들 생성
pnpm start        # 빌드 결과 실행
pnpm lint         # ESLint 검사 (현재 next.config에서 빌드 시 무시 설정)
```

### 환경 변수
Supabase 인증 기능을 사용하려면 루트의 `.env.local`에 아래 값을 설정하세요.

```bash
NEXT_PUBLIC_SUPABASE_URL=https://your-project-ref.supabase.co
NEXT_PUBLIC_SUPABASE_ANON_KEY=your-anon-key
```

`NEXT_PUBLIC_SUPABASE_URL`에는 `https://supabase.com/dashboard/...` 같은 Dashboard 페이지 주소를 넣으면 안 됩니다. Supabase 프로젝트의 API URL을 사용해야 합니다.

### 테스트
Jest + React Testing Library 구성이 포함되어 있지만 초기 테스트 파일은 없습니다. 새로운 테스트를 추가하려면 `jest.config.mjs`와 `jest.setup.js`를 참고하여 `__tests__` 혹은 컴포넌트 인접 위치에 `.test.tsx` 파일을 작성하세요.

## 프로젝트 구조 개요
```
app/                # Next.js App Router 엔트리 (app/page.tsx에서 보드 렌더)
components/         # shadcn/ui 기반 재사용 컴포넌트
crypto-ranking-board.tsx  # 핵심 보드 UI와 목업 데이터 정의
hooks/, lib/        # 커스텀 훅 및 유틸 (필요 시 확장)
public/coin/        # 코인 로고 에셋
styles/, tailwind.config.ts  # 스타일 설정
```

## 데이터 갱신 및 확장
- 현재 `crypto-ranking-board.tsx`에는 `mockCoinData` 배열이 하드코딩되어 있습니다.
- 실시간 API를 붙일 경우 `CoinData` 인터페이스를 유지하면서 fetch 로직을 추가하고, API 키는 환경 변수(`.env.local`)로 관리하세요.
- 새로운 코인을 추가하려면 `public/coin/<name>.png`(또는 .webp) 파일을 추가한 뒤 `mockCoinData` 배열에 항목을 넣습니다.

## 추가 참고 사항
1. `next.config.mjs`에서 TypeScript 및 ESLint 오류를 빌드 시 무시하도록 설정되어 있습니다. 배포 단계에서는 `ignoreDuringBuilds` 옵션을 `false`로 바꾸는 것을 권장합니다.
2. 아직 공식 README가 없었기 때문에 본 파일이 기본 문서 역할을 합니다. 팀 가이드라인은 `CLAUDE.md`에, 에이전트 응답 규칙은 `AGENTS*.md` 파일을 참고하세요.
3. UI 컴포넌트는 shadcn/ui 템플릿에 기반하므로 필요 시 `components.json`을 이용해 컴포넌트를 재생성하거나 업데이트할 수 있습니다.

## 향후 작업 아이디어
- CoinAPI 연동 및 실시간 데이터 폴링
- 즐겨찾기/필터/검색 기능 추가
- Recharts를 활용한 차트 섹션 구현
- Jest 스냅샷 및 상호작용 테스트 작성

## docs 폴더
- `resolve-issue.md`: 이슈 해결 계획 작성 가이드라인 문서입니다.
- `CLAUDE.md`: 팀 가이드라인 및 규칙 문서입니다.
- `AGENTS.md`, `AGENTS2.md`: 에이전트 응답 규
- 칙 및 행동 지침 문서입니다.
