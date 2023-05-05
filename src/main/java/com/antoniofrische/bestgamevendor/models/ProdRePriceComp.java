package com.antoniofrische.bestgamevendor.models;

import java.util.Comparator;

public class  ProdRePriceComp implements Comparator<ProductosEntity> {
        @Override
        public int compare(ProductosEntity a, ProductosEntity b) {
            return a.getPrecioSalida().compareTo(b.getPrecioSalida());
        }
}
