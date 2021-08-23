package homework.lesson3.parametrizecountmap;

import java.util.Map;

public interface CountMap<K> {
    // добавляет элемент в этот контейнер.
    void add(K k);

    //Возвращает количество добавлений данного элемента
    int getCount(K k);

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    int remove(K k);

    //количество разных элементов
    int size();

    //Добавить все элементы из source в текущий контейнер,
    // при совпадении ключей,     суммировать значения
    void addAll(CountMap source);

    //Вернуть java.util.Map. ключ - добавленный элемент,
    // значение - количество его добавлений
    Map toMap();

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    void toMap(Map destination);
}

