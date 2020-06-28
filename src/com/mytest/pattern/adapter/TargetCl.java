package com.mytest.pattern.adapter;

/**
 * @Author murongyunge
 * @Describe   Target（目标抽象类）：具体的类
 * @Date 2019-12-09
 */
public class TargetCl {
    private Adapter3 adapter3;

    public void setAdapter(Adapter3 adapter){
        this.adapter3 = adapter;
    }

    public void request(){
        adapter3.adapteeRequest();
    }

}
