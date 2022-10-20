package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Firm type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Firms", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Firm implements Model {
  public static final QueryField ID = field("Firm", "id");
  public static final QueryField NAME = field("Firm", "name");
  public static final QueryField CITY_AND_STATE = field("Firm", "cityAndState");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String") String cityAndState;
  private final @ModelField(targetType="User") @HasMany(associatedWith = "firm", type = User.class) List<User> users = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getCityAndState() {
      return cityAndState;
  }
  
  public List<User> getUsers() {
      return users;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Firm(String id, String name, String cityAndState) {
    this.id = id;
    this.name = name;
    this.cityAndState = cityAndState;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Firm firm = (Firm) obj;
      return ObjectsCompat.equals(getId(), firm.getId()) &&
              ObjectsCompat.equals(getName(), firm.getName()) &&
              ObjectsCompat.equals(getCityAndState(), firm.getCityAndState()) &&
              ObjectsCompat.equals(getCreatedAt(), firm.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), firm.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getCityAndState())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Firm {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("cityAndState=" + String.valueOf(getCityAndState()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
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
  public static Firm justId(String id) {
    return new Firm(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      cityAndState);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Firm build();
    BuildStep id(String id);
    BuildStep cityAndState(String cityAndState);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private String cityAndState;
    @Override
     public Firm build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Firm(
          id,
          name,
          cityAndState);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep cityAndState(String cityAndState) {
        this.cityAndState = cityAndState;
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
    private CopyOfBuilder(String id, String name, String cityAndState) {
      super.id(id);
      super.name(name)
        .cityAndState(cityAndState);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder cityAndState(String cityAndState) {
      return (CopyOfBuilder) super.cityAndState(cityAndState);
    }
  }
  
}
