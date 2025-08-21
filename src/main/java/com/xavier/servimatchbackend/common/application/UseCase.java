package com.xavier.servimatchbackend.common.application;

public interface UseCase<I, O> {

    /**
     * Executes the use case with the provided input.
     *
     * @param input the input for the use case
     * @return the output of the use case
     */
     O execute(I input);

    /**
     * Validates the input for the use case.
     *
     * @param input the input to validate
     * @return true if the input is valid, false otherwise
     */
    boolean validate(I input);
}
