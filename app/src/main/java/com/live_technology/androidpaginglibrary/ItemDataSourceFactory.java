package com.live_technology.androidpaginglibrary;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {
    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, StackApiResponse.Item>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, StackApiResponse.Item> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource();

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, StackApiResponse.Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
