package study.java.collection.map;

public class A_Map {
    /*
    * -Map
    * 키와 값으로 구성된 Entry 객체를 저장하는 구조이다.
    * 키 값은 중복을 허용하지 않고 값은 중복을 허용한다.
    * 종류로는 HashMap, Hashtable, LinkedHashMap, Properties, TreeMap 등이 있다.
    * Map의 관련된 메소드들은 매개값으로 key를 가지는 경우가 많다.
    *
    * value값을 알고 싶을때는 key값으로 찾으면 되지만 key값을 모를 경우 찾는 방법은 아래와 같이 Key를 Set으로 받아서 Iterator로 접근하는 방법이 있다.
    * Map<K,V> map = ...
    * Set<K> keySet = map.keySet();
    * Iterator<K> keyIterator = keySet.iterator();
    * while(keyIterator.hasNext()){
    *   K key = keyIterator.next();
    *   V value = map.get(key);
    * }
    *
    * -HashMap
    * hashCode(), equals()메소드를 이용해서 key값으로 받을 객체를 동등비교하여 저장한다.(보통 key 값으로 String을 사용하는데 String은 정의 되어있다.)
    *
    * -Hashtable
    * HashMap과 동일한 구조이지만 동기화된 메소드이다. 그러므로 멀티스레드로부터 객체가 안전하다.
    *
    * -Properties
    * Hashtable과 같은 구조이지만 key와 value 타입이 String로 제한되어있는 map이다
    * 주로 데이터베이스 연결 정보, 국제화 정보를 저장하는 용도로 사용되며 해당 파일을 읽어 올떄는 FileReader객체의 매개값으로 받아서 읽어올수 있다.
    *
    * */
}
