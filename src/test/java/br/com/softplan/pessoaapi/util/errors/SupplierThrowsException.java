package br.com.softplan.pessoaapi.util.errors;

@FunctionalInterface
public interface SupplierThrowsException<T> {

    T get() throws Exception;

}
