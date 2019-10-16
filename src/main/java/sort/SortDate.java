package sort;

import dolla.task.Entry;

import java.util.ArrayList;
import java.util.Collections;

public class SortDate extends Sort{
//    ArrayList<Entry> sortedEntriesList;
    ArrayList<Entry> entriesToSort;

//    public SortDate(ArrayList<Entry> entriesToSort) {
//        this.entriesToSort = entriesToSort;
//        sort();
//        printSortedList();
//    }
    public void setEntriesToSort(ArrayList<Entry> entriesToSort) {
        this.entriesToSort = entriesToSort;
        sort();
        printSortedList();
    }

    @Override
    public void printSortedList() {
        for (int i = 0; i < entriesToSort.size(); i++) {
            int listNum = i + 1;
            System.out.println("\t" + listNum + ". " + entriesToSort.get(i).getLogText());
        }
    }

    public ArrayList<Entry> sort() {
        System.out.println("here");
        Collections.sort(entriesToSort,DateComparator.entryDateComparator());
        return entriesToSort;
    }

}
