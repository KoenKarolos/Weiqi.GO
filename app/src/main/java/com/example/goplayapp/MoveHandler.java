package com.example.goplayapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MoveHandler {

        private static com.example.goplayapp.MoveHandler instance;

        public static com.example.goplayapp.MoveHandler getInstance() {
            if (instance == null)
                instance = new com.example.goplayapp.MoveHandler();
            return instance;
        }

        private MoveHandler() {

        }
        private Integer currentTurn = 1;

        Map<Integer, List<Integer>> gameHistory = new HashMap<>(); // Dictionary {turn : [posX,posY]}

        public void addMove(Integer column, Integer row ) {
            List<Integer> position = new ArrayList<>();
            position.add(column);
            position.add(row);
            this.gameHistory.put(currentTurn,position);
            currentTurn ++;
        }

        public Map<java.lang.Integer,java.util.List<java.lang.Integer>> getMoves() { return gameHistory; }

        public void wipeMoves(){
            this.instance = null;
        }
}
