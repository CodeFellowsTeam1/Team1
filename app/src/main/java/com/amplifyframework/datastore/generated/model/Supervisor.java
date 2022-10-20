package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasOne;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Supervisor type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Supervisors", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Supervisor implements Model {
  public static final QueryField ID = field("Supervisor", "id");
  public static final QueryField SUPERVISOR_USERS_ID = field("Supervisor", "supervisorUsersId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="User", isRequired = true) @HasOne(associatedWith = "id", type = User.class) User users = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID", isRequired = true) String supervisorUsersId;
  public String getId() {
      return id;
  }
  
  public User getUsers() {
      return users;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getSupervisorUsersId() {
      return supervisorUsersId;
  }
  
  private Supervisor(String id, String supervisorUsersId) {
    this.id = id;
    this.supervisorUsersId = supervisorUsersId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Supervisor supervisor = (Supervisor) obj;
      return ObjectsCompat.equals(getId(), supervisor.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), supervisor.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), supervisor.getUpdatedAt()) &&
              ObjectsCompat.equals(getSupervisorUsersId(), supervisor.getSupervisorUsersId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getSupervisorUsersId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Supervisor {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("supervisorUsersId=" + String.valueOf(getSupervisorUsersId()))
      .append("}")
      .toString();
  }
  
  public static SupervisorUsersIdStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Supervisor justId(String id) {
    return new Supervisor(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      supervisorUsersId);
  }
  public interface SupervisorUsersIdStep {
    BuildStep supervisorUsersId(String supervisorUsersId);
  }
  

  public interface BuildStep {
    Supervisor build();
    BuildStep id(String id);
  }
  

  public static class Builder implements SupervisorUsersIdStep, BuildStep {
    private String id;
    private String supervisorUsersId;
    @Override
     public Supervisor build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Supervisor(
          id,
          supervisorUsersId);
    }
    
    @Override
     public BuildStep supervisorUsersId(String supervisorUsersId) {
        Objects.requireNonNull(supervisorUsersId);
        this.supervisorUsersId = supervisorUsersId;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String supervisorUsersId) {
      super.id(id);
      super.supervisorUsersId(supervisorUsersId);
    }
    
    @Override
     public CopyOfBuilder supervisorUsersId(String supervisorUsersId) {
      return (CopyOfBuilder) super.supervisorUsersId(supervisorUsersId);
    }
  }
  
}
