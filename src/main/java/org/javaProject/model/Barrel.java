package org.javaProject.model;

import java.util.Comparator;
import java.util.Objects;

public class Barrel implements Comparable<Barrel>{
    private long volume;
    private String content;
    private String barrelMaterial;

    public Barrel(BarrelBuilder barrelBuilder) {
        volume = barrelBuilder.volume;
        content = barrelBuilder.content;
        barrelMaterial = barrelBuilder.barrelMaterial;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBarrelMaterial() {
        return barrelMaterial;
    }

    public void setBarrelMaterial(String barrelMaterial) {
        this.barrelMaterial = barrelMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrel barrel = (Barrel) o;
        return volume == barrel.volume && Objects.equals(content, barrel.content) && Objects.equals(barrelMaterial, barrel.barrelMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, content, barrelMaterial);
    }

    @Override
    public int compareTo(Barrel o) {
        return Comparator.comparing(Barrel::getVolume)
                .thenComparing(Barrel::getContent)
                .thenComparing(Barrel::getBarrelMaterial).compare(this,o);
    }

    @Override
    public String toString() {
        return "Barrel{" +
                "volume=" + volume +
                ", content='" + content + '\'' +
                ", barrelMaterial='" + barrelMaterial + '\'' +
                '}';
    }

    public static BarrelBuilder builder(){
        return new BarrelBuilder();
    }

    public static class BarrelBuilder{
        private long volume;
        private String content;
        private String barrelMaterial;

        public BarrelBuilder setVolume(long volume) {
            this.volume = volume;
            return this;
        }

        public BarrelBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public BarrelBuilder setBarrelMaterial(String barrelMaterial) {
            this.barrelMaterial = barrelMaterial;
            return this;
        }

        public Barrel build(){
            return new Barrel(this);
        }
    }
}
