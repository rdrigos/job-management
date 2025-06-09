package br.com.rdrigos.job_management.exceptions;

public class CompanyFoundException extends RuntimeException {
    public CompanyFoundException() {
        super("Company already exists");
    }
}
