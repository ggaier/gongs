package com.github.ggaier.gongs;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;

/** Created by wenbo, 2018/10/11 */
public class Test {

  @Deprecated(
      message = "不再继续使用",
      replaceWith =
          @ReplaceWith(
              imports = {"com.github.ggaier.nestedrecyclerview.Test"},
              expression = "add1()"),
      level = DeprecationLevel.HIDDEN)
  public void add() {}

  public void add1() {}

  public static void main(String... args){
      Test test = new Test();
      test.add();
  }
}
