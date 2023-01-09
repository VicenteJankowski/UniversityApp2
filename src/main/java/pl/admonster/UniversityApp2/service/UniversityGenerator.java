package pl.admonster.UniversityApp2.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniversityGenerator {

    private UniversityGenerator(){}
    public final static void generate(Class modelClass, int requestedCount) throws FileNotFoundException {

        List<Field> fieldsOfModelClass = new ArrayList<>();
        fieldsOfModelClass.addAll(List.of(modelClass.getDeclaredFields()));

        Class superClassOfModel = modelClass.getSuperclass();
        while (superClassOfModel != null) {
            fieldsOfModelClass.addAll(List.of(superClassOfModel.getDeclaredFields()));
            superClassOfModel = superClassOfModel.getSuperclass();
        }

        System.out.println("Siema, liczba pól klasy " + modelClass.getName() + " to " + fieldsOfModelClass.size());

        for (Field singleField : fieldsOfModelClass) {
            System.out.println("Nazwa odnalezionego pola to: " + singleField.getName());
        }
    }

}
