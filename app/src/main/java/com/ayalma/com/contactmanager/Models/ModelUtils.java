package com.ayalma.com.contactmanager.Models;

import java.util.List;

/**
 * Created by ali on 11/7/15.
 */
public class ModelUtils {
        public interface DbCrudInterface{
        public int Insert(Object dbObject);
        public boolean Update(Object dbObject);
        public Object Get(Object dbObject , int modelId);
        public List<Object> GetAll(Object o);
        public boolean Delete(Object dbObject);

    }
}
