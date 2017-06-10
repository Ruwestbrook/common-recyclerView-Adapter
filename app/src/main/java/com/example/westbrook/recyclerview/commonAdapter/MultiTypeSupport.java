package com.example.westbrook.recyclerview.commonAdapter;

/**
 * Created by westbrook on 2017/6/9.
 * 多条目多布局的支持
 */

public interface MultiTypeSupport<T> {
  int getLayoutId(T item);
}
