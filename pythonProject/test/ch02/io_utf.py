import io

f = io.open("abc.txt", "wt", encoding="utf-8")
f.write(u"Imagine non-English language here 테스트")
f.close()

text = io.open("abc.txt", encoding="utf-8").read()
print(text)