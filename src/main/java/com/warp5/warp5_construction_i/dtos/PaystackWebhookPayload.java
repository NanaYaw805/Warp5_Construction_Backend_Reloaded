package com.warp5.warp5_construction_i.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaystackWebhookPayload {

    private String event;
    private PaystackData data;

    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

    public PaystackData getData() { return data; }
    public void setData(PaystackData data) { this.data = data; }

    public static class PaystackData {
        private Long id;
        private Long amount;
        private String currency;
        private String status;
        private String reference;
        private Customer customer;
        private Metadata metadata;

        // getters & setters


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public void setMetadata(Metadata metadata) {
            this.metadata = metadata;
        }

        public static class Customer {
            private String email;
            private Long id;

            // getters & setters
        }

        public static class Metadata {
            @JsonProperty("rentalRequestId")
            private Long rentalRequestId;

            // getters & setters


            public Long getRentalRequestId() {
                return rentalRequestId;
            }

            public void setRentalRequestId(Long rentalRequestId) {
                this.rentalRequestId = rentalRequestId;
            }
        }
    }
}