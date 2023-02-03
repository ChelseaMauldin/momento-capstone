<template>
  <div class="edit-main">
    <form>
      <div class="form-group">
        <label for="username-input">Username</label>
        <input
          type="text"
          class="form-control"
          id="username-input"
          placeholder="Enter new username"
        />
      </div>
      <div class="form-group">
        <label for="name-input">Name</label>
        <input
          type="text"
          class="form-control"
          id="name-input"
          placeholder="Enter new display name"
        />
      </div>
      <div class="form-group">
        <label for="bio-input">Biography</label>
        <textarea
          class="form-control"
          id="bio-input"
          rows="3"
        ></textarea>
      </div>
      <div class="form-group">
        <label for="email-input">Email Address</label>
        <input
          type="email"
          class="form-control"
          id="email-input"
          placeholder="Enter new email"
        />
      </div>

      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
export default {};
</script>

<style>
.edit-main {
  width: 30%;
  margin-top: 30px;
  margin-left: auto;
  margin-right: auto;
}
</style>