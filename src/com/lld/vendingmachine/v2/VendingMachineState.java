package com.lld.vendingmachine.v2;

import com.lld.vendingmachine.v1.Coin;
import com.lld.vendingmachine.v1.Note;

public interface VendingMachineState {
    void insertCoin ( Coin coin );

    void insertNote ( Note note );

    void selectProduct ( Product product );

    void dispenseProduct ();

    void cancelTransaction ();
}
