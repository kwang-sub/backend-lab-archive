# Repository Guidelines

이 문서는 암호화폐 순위 대시보드에 효율적으로 기여하기 위한 핵심 가이드를 정리한 것입니다. 아래 섹션을 따르며 프로젝트 전반의 일관성을 유지하세요.
모든 응답은 한국어로 작성해주세요.

## 프로젝트 구조 및 모듈 구성
- `app/`에는 Next.js App Router 진입점인 `app/page.tsx`가 있으며, 내부에서 `crypto-ranking-board.tsx`를 렌더링합니다.
- `crypto-ranking-board.tsx`는 메인 대시보드 UI, `CoinData` 인터페이스, 목업 데이터를 포함합니다.
- `components/`, `hooks/`, `lib/`에는 shadcn/ui 도구로 생성된 재사용 가능한 UI 블록, 훅, 헬퍼가 들어 있습니다.
- 코인 로고 등 에셋은 `public/coin/` 아래에 저장되며, 전역 스타일 설정은 `styles/`, `tailwind.config.ts`, `components.json`에 정의됩니다.

## 빌드·테스트·개발 명령
```bash
pnpm install   # 의존성 설치
pnpm dev       # http://localhost:3000에서 Next.js 개발 서버 실행
pnpm build     # 프로덕션 번들 생성 (현 시점 TS/ESLint 오류는 next.config.mjs에서 무시)
pnpm start     # .next 결과물을 이용해 프로덕션 서버 실행
pnpm lint      # 로컬에서 Next.js lint 규칙 실행
pnpm test      # Jest 테스트 실행(테스트 케이스 추가 후 사용)
```

## 코딩 스타일 및 네이밍 규칙
- TypeScript 기반 React 함수형 컴포넌트/훅을 사용하고 엄격한 타이핑을 유지합니다. 컴포넌트는 PascalCase, 유틸리티는 camelCase로 명명합니다.
- Tailwind 유틸리티 클래스는 레이아웃 → 색상 → 타이포 순서로 묶어 가독성을 확보합니다.
- 들여쓰기는 2칸을 사용하고 Prettier 기본값을 따르며, 공통 유틸리티는 `@/` 별칭을 통해 재노출합니다.

## 테스트 가이드라인
- Jest와 `@testing-library/react`가 `jest.config.mjs`, `jest.setup.js`에 미리 설정되어 있으나 아직 테스트 슈트는 없습니다.
- 테스트 파일은 대상 컴포넌트와 같은 위치에 `*.test.tsx`로 두거나 `__tests__/` 디렉터리를 사용합니다.
- API 호출과 타이머는 모킹하고, 표 렌더링·포맷팅 헬퍼 등 핵심 플로우에 대한 렌더링/상호작용 검증을 목표로 합니다.

## 커밋 및 PR 가이드라인
- Git 기록에는 현재 `init` 하나만 있으므로 동일하게 짧은 소문자 명령형 커밋(예: `add coin list`, `fix lint`)을 권장합니다.
- PR에는 변경 요약, 테스트 실행 결과(명령 + 출력), UI 수정 시 스크린샷/ GIF를 포함합니다.
- 관련 이슈를 링크하고, 설정 변경이나 마이그레이션 단계가 있다면 PR 설명에 명시하세요.

## 보안 및 설정 팁
- API 키는 `.env.local`에 보관하며 절대 커밋하지 않습니다. 코드에서는 `process.env`를 통해 참조하세요.
- 배포 전에는 `next.config.mjs`의 TypeScript/ESLint 빌드 차단 기능을 다시 활성화해 회귀를 조기에 포착하는 것이 좋습니다.
