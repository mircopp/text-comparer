package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirco on 30.09.2016.
 */
public class TwoDimensionalMap <K,V>{

    private Map<K, Map <K, V>> map;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public TwoDimensionalMap (){
        map = new HashMap<K, Map<K, V>>();
    }

    public void putValue(K first, K second, V value){
        if(map.get(first) == null){
            Map <K,V> temp = new HashMap<K,V>();
            temp.put(second, value);
            map.put(first, temp);
        }
        else{
            map.get(first).put(second, value);
        }
    }

    public V getValue(K first, K second){
        V value = map.get(first).get(second);
        return value;
    }
}
