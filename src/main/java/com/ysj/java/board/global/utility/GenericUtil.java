package com.ysj.java.board.global.utility;

import java.util.List;

public class GenericUtil<T>
{
  public void swap(List<T> list, int index1, int index2)
  {
    T temp = list.get(index1);
    list.set(index1, list.get(index2));
    list.set(index2, temp);
  }

  public List<T> sortReverse(List<T> list)
  {
    for(int a = 0; a < list.size() / 2; a++)
    {
      swap(list, a, (list.size() - 1) - a);
    }

    return list;
  }
}
