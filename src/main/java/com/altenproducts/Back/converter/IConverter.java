package com.altenproducts.Back.converter;

interface IConverter <T, X> {

    X toForm(T var);
}