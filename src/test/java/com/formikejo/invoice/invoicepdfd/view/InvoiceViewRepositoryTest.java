package com.formikejo.invoice.invoicepdfd.view;

import com.formikejo.invoice.invoicepdfd.InvoiceViewRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ulm.Clemens on 26-Jun-15.
 */
public class InvoiceViewRepositoryTest {

    private InvoiceViewRepository repository;

    @Before
    public void setUp() {
        repository = new InvoiceViewRepository();

    }

    @Test
    public void testSimpleStoreAndRetrieve() {
        InvoiceView view = new InvoiceView(null, null, null, null, null, null, null, "Style 1");
        UUID id = repository.store(view);
        InvoiceView retrieved = repository.get(id);

        assertThat(retrieved, is(view));
    }

}
