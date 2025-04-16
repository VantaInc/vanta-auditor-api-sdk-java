/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

public class Comment {

    /**
     * The unique identifier for the comment
     */
    @JsonProperty("id")
    private String id;

    /**
     * The unique identifier for the audit evidence related to the comment.
     */
    @JsonProperty("auditEvidenceId")
    private String auditEvidenceId;

    /**
     * The comment message
     */
    @JsonProperty("text")
    private String text;

    /**
     * When the comment was created
     */
    @JsonProperty("creationDate")
    private OffsetDateTime creationDate;

    /**
     * When the comment was updated
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("modificationDate")
    private Optional<OffsetDateTime> modificationDate;

    /**
     * When the comment was deleted
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("deletionDate")
    private Optional<OffsetDateTime> deletionDate;

    /**
     * The email of the comment author. This acts as a unique identifier to map users between Vanta and external systems.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("email")
    private Optional<String> email;

    @JsonCreator
    public Comment(
            @JsonProperty("id") String id,
            @JsonProperty("auditEvidenceId") String auditEvidenceId,
            @JsonProperty("text") String text,
            @JsonProperty("creationDate") OffsetDateTime creationDate,
            @JsonProperty("modificationDate") Optional<OffsetDateTime> modificationDate,
            @JsonProperty("deletionDate") Optional<OffsetDateTime> deletionDate,
            @JsonProperty("email") Optional<String> email) {
        Utils.checkNotNull(id, "id");
        Utils.checkNotNull(auditEvidenceId, "auditEvidenceId");
        Utils.checkNotNull(text, "text");
        Utils.checkNotNull(creationDate, "creationDate");
        Utils.checkNotNull(modificationDate, "modificationDate");
        Utils.checkNotNull(deletionDate, "deletionDate");
        Utils.checkNotNull(email, "email");
        this.id = id;
        this.auditEvidenceId = auditEvidenceId;
        this.text = text;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.deletionDate = deletionDate;
        this.email = email;
    }
    
    public Comment(
            String id,
            String auditEvidenceId,
            String text,
            OffsetDateTime creationDate) {
        this(id, auditEvidenceId, text, creationDate, Optional.empty(), Optional.empty(), Optional.empty());
    }

    /**
     * The unique identifier for the comment
     */
    @JsonIgnore
    public String id() {
        return id;
    }

    /**
     * The unique identifier for the audit evidence related to the comment.
     */
    @JsonIgnore
    public String auditEvidenceId() {
        return auditEvidenceId;
    }

    /**
     * The comment message
     */
    @JsonIgnore
    public String text() {
        return text;
    }

    /**
     * When the comment was created
     */
    @JsonIgnore
    public OffsetDateTime creationDate() {
        return creationDate;
    }

    /**
     * When the comment was updated
     */
    @JsonIgnore
    public Optional<OffsetDateTime> modificationDate() {
        return modificationDate;
    }

    /**
     * When the comment was deleted
     */
    @JsonIgnore
    public Optional<OffsetDateTime> deletionDate() {
        return deletionDate;
    }

    /**
     * The email of the comment author. This acts as a unique identifier to map users between Vanta and external systems.
     */
    @JsonIgnore
    public Optional<String> email() {
        return email;
    }

    public final static Builder builder() {
        return new Builder();
    }    

    /**
     * The unique identifier for the comment
     */
    public Comment withId(String id) {
        Utils.checkNotNull(id, "id");
        this.id = id;
        return this;
    }

    /**
     * The unique identifier for the audit evidence related to the comment.
     */
    public Comment withAuditEvidenceId(String auditEvidenceId) {
        Utils.checkNotNull(auditEvidenceId, "auditEvidenceId");
        this.auditEvidenceId = auditEvidenceId;
        return this;
    }

    /**
     * The comment message
     */
    public Comment withText(String text) {
        Utils.checkNotNull(text, "text");
        this.text = text;
        return this;
    }

    /**
     * When the comment was created
     */
    public Comment withCreationDate(OffsetDateTime creationDate) {
        Utils.checkNotNull(creationDate, "creationDate");
        this.creationDate = creationDate;
        return this;
    }

    /**
     * When the comment was updated
     */
    public Comment withModificationDate(OffsetDateTime modificationDate) {
        Utils.checkNotNull(modificationDate, "modificationDate");
        this.modificationDate = Optional.ofNullable(modificationDate);
        return this;
    }

    /**
     * When the comment was updated
     */
    public Comment withModificationDate(Optional<OffsetDateTime> modificationDate) {
        Utils.checkNotNull(modificationDate, "modificationDate");
        this.modificationDate = modificationDate;
        return this;
    }

    /**
     * When the comment was deleted
     */
    public Comment withDeletionDate(OffsetDateTime deletionDate) {
        Utils.checkNotNull(deletionDate, "deletionDate");
        this.deletionDate = Optional.ofNullable(deletionDate);
        return this;
    }

    /**
     * When the comment was deleted
     */
    public Comment withDeletionDate(Optional<OffsetDateTime> deletionDate) {
        Utils.checkNotNull(deletionDate, "deletionDate");
        this.deletionDate = deletionDate;
        return this;
    }

    /**
     * The email of the comment author. This acts as a unique identifier to map users between Vanta and external systems.
     */
    public Comment withEmail(String email) {
        Utils.checkNotNull(email, "email");
        this.email = Optional.ofNullable(email);
        return this;
    }

    /**
     * The email of the comment author. This acts as a unique identifier to map users between Vanta and external systems.
     */
    public Comment withEmail(Optional<String> email) {
        Utils.checkNotNull(email, "email");
        this.email = email;
        return this;
    }

    
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment other = (Comment) o;
        return 
            Objects.deepEquals(this.id, other.id) &&
            Objects.deepEquals(this.auditEvidenceId, other.auditEvidenceId) &&
            Objects.deepEquals(this.text, other.text) &&
            Objects.deepEquals(this.creationDate, other.creationDate) &&
            Objects.deepEquals(this.modificationDate, other.modificationDate) &&
            Objects.deepEquals(this.deletionDate, other.deletionDate) &&
            Objects.deepEquals(this.email, other.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            auditEvidenceId,
            text,
            creationDate,
            modificationDate,
            deletionDate,
            email);
    }
    
    @Override
    public String toString() {
        return Utils.toString(Comment.class,
                "id", id,
                "auditEvidenceId", auditEvidenceId,
                "text", text,
                "creationDate", creationDate,
                "modificationDate", modificationDate,
                "deletionDate", deletionDate,
                "email", email);
    }
    
    public final static class Builder {
 
        private String id;
 
        private String auditEvidenceId;
 
        private String text;
 
        private OffsetDateTime creationDate;
 
        private Optional<OffsetDateTime> modificationDate = Optional.empty();
 
        private Optional<OffsetDateTime> deletionDate = Optional.empty();
 
        private Optional<String> email = Optional.empty();
        
        private Builder() {
          // force use of static builder() method
        }

        /**
         * The unique identifier for the comment
         */
        public Builder id(String id) {
            Utils.checkNotNull(id, "id");
            this.id = id;
            return this;
        }

        /**
         * The unique identifier for the audit evidence related to the comment.
         */
        public Builder auditEvidenceId(String auditEvidenceId) {
            Utils.checkNotNull(auditEvidenceId, "auditEvidenceId");
            this.auditEvidenceId = auditEvidenceId;
            return this;
        }

        /**
         * The comment message
         */
        public Builder text(String text) {
            Utils.checkNotNull(text, "text");
            this.text = text;
            return this;
        }

        /**
         * When the comment was created
         */
        public Builder creationDate(OffsetDateTime creationDate) {
            Utils.checkNotNull(creationDate, "creationDate");
            this.creationDate = creationDate;
            return this;
        }

        /**
         * When the comment was updated
         */
        public Builder modificationDate(OffsetDateTime modificationDate) {
            Utils.checkNotNull(modificationDate, "modificationDate");
            this.modificationDate = Optional.ofNullable(modificationDate);
            return this;
        }

        /**
         * When the comment was updated
         */
        public Builder modificationDate(Optional<OffsetDateTime> modificationDate) {
            Utils.checkNotNull(modificationDate, "modificationDate");
            this.modificationDate = modificationDate;
            return this;
        }

        /**
         * When the comment was deleted
         */
        public Builder deletionDate(OffsetDateTime deletionDate) {
            Utils.checkNotNull(deletionDate, "deletionDate");
            this.deletionDate = Optional.ofNullable(deletionDate);
            return this;
        }

        /**
         * When the comment was deleted
         */
        public Builder deletionDate(Optional<OffsetDateTime> deletionDate) {
            Utils.checkNotNull(deletionDate, "deletionDate");
            this.deletionDate = deletionDate;
            return this;
        }

        /**
         * The email of the comment author. This acts as a unique identifier to map users between Vanta and external systems.
         */
        public Builder email(String email) {
            Utils.checkNotNull(email, "email");
            this.email = Optional.ofNullable(email);
            return this;
        }

        /**
         * The email of the comment author. This acts as a unique identifier to map users between Vanta and external systems.
         */
        public Builder email(Optional<String> email) {
            Utils.checkNotNull(email, "email");
            this.email = email;
            return this;
        }
        
        public Comment build() {
            return new Comment(
                id,
                auditEvidenceId,
                text,
                creationDate,
                modificationDate,
                deletionDate,
                email);
        }
    }
}
