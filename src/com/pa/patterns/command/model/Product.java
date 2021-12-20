package com.pa.patterns.command.model;

import java.util.Objects;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " (" + cost + ')';
    }


    public static Product create(String name, double cost){
         return new Product(name,cost);
    }

    public  Product copy(Product p){
         return new Product(p.name,p.cost);
    }

    public static Product build(boolean type,String name, double cost){
        if(type)
            return new Product(name,0);
        else
            return new Product(name,cost);
    }

    public  Product rebuild(double cost){
         this.cost=cost;
         return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }
}
