package com.altenproducts.Back.domain.port;

public interface AltenInterface <T>{
    public T getDataById(int id);
    public boolean deleteOne(int id);
}
