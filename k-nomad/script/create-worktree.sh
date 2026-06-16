#!/bin/bash

set -u

WAIT_ON_EXIT=true

print_message() {
  echo "$1"
}

pause_if_needed() {
  if [ "$WAIT_ON_EXIT" = true ]; then
    printf '\n계속하려면 Enter 키를 누르세요...'
    read -r _
  fi
}

error() {
  print_message "Error: $1" >&2
  pause_if_needed
  exit "${2:-1}"
}

usage() {
  cat <<'EOF'
사용법:
  ./script/create-worktree.sh [--wait] <worktree-name>

설명:
  현재 프로젝트용 외부 워크트리를 생성합니다.

옵션:
  --wait, -w     다음 단계 진행 전에 Enter 입력을 기다립니다.
EOF
  pause_if_needed
  exit 1
}

while [ $# -gt 0 ]; do
  case "$1" in
    --wait|-w)
      WAIT_ON_EXIT=true
      shift
      ;;
    --help|-h)
      usage
      ;;
    -*)
      error "알 수 없는 옵션입니다: $1"
      ;;
    *)
      break
      ;;
  esac
done

if [ $# -eq 0 ]; then
  error "워크트리 이름을 입력해주세요."
fi

if ! command -v git >/dev/null 2>&1; then
  error "git 명령을 찾을 수 없습니다."
fi

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
REPO_PARENT_DIR="$(dirname "$REPO_ROOT")"
REPO_NAME="$(basename "$REPO_ROOT")"

if ! git -C "$REPO_ROOT" rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  error "Git 저장소 내부에서만 실행할 수 있습니다."
fi

WORKTREE_NAME="$1"
WORKTREE_BASE_PATH="$REPO_PARENT_DIR/worktree/$REPO_NAME"
WORKTREE_PATH="$WORKTREE_BASE_PATH/$WORKTREE_NAME"

if [ -z "$WORKTREE_NAME" ]; then
  error "유효한 워크트리 이름을 입력해주세요."
fi

if [ -e "$WORKTREE_PATH" ]; then
  error "이미 같은 이름의 경로가 존재합니다: $WORKTREE_PATH"
fi

mkdir -p "$WORKTREE_BASE_PATH" || error "워크트리 디렉터리를 생성할 수 없습니다: $WORKTREE_BASE_PATH"

if ! git -C "$REPO_ROOT" worktree add "$WORKTREE_PATH"; then
  error "워크트리 생성에 실패했습니다. 브랜치 상태와 기존 워크트리 목록을 확인해주세요."
fi

print_message "워크트리가 성공적으로 생성되었습니다: $WORKTREE_PATH"

cd "$WORKTREE_PATH" || error "생성된 워크트리로 이동할 수 없습니다: $WORKTREE_PATH"
print_message "현재 위치: $(pwd)"

pause_if_needed
