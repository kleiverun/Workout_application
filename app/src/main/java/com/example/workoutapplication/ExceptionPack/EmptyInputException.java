package com.example.workoutapplication.ExceptionPack;

public class EmptyInputException extends Exception{

        public EmptyInputException () {

        }

        public EmptyInputException (String message) {
            super (message);
        }

        public EmptyInputException (Throwable cause) {
            super (cause);
        }

        public EmptyInputException (String message, Throwable cause) {
            super (message, cause);
        }
    }

