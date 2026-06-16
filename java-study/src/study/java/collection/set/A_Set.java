package study.java.collection.set;

public class A_Set {
    /*
    * -Set
    * 저장 순서를 유지하지 않고 중복을 허용하지 않는 특징을 가지고 있다.
    * 종류에는 HashSet, LinkedHashSet, TreeSet등이 있다.
    * 인덱스를 가지고 있지 않기 때문에 메소드를 호출할때 매개변수로 인덱스 값을 가지는 메소드가 없다.
    * Set의 있는 객체를 가져오기 위해서는 반복자(Iterator)를 이용해야 된다. 또는 향상된 for문을 이용하는 방법이 있다.
    *
    * -HashSet
    * 객체를 저장할때 HashCode() 메소드를 호출해서 해시코드를 얻어낸 다음 이전 객체와 비교한다
    * 그 중 해시코드가 같을 경우 equals() 메소드로 두 객체를 비교해서 중복을 방지한 다음 저장한다.
    * 따라서 HashSet을 이용할경우 해당 메소드들을 재정의(오버라이딩)해줘야 한다.
    *
    *
    * */
}
