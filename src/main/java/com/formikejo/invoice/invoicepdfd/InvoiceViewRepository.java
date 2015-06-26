package com.formikejo.invoice.invoicepdfd;

import com.formikejo.invoice.invoicepdfd.view.InvoiceView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class InvoiceViewRepository {
	public Map<UUID, InvoiceView> map = new HashMap<UUID, InvoiceView>();

	public UUID store(InvoiceView view) {
		UUID id = UUID.randomUUID();
		map.put(id, view);
		return id;
	}

	public InvoiceView get(UUID uuid) {
		return map.get(uuid);
	}

    public int countSuccess() {
        return map.size();
    }
}

