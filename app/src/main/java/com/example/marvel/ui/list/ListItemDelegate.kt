package com.example.marvel.ui.list

import com.example.marvel.repository.model.ResultsItem


interface ListItemDelegate {
    fun onItemClick(item: ResultsItem)
}