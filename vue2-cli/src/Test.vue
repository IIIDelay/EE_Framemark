<template>
  <div class="test-bom">
    <h1>
      这是一个Test.vue组件,名称： -- {{ username }} </h1>
    <button @click="updateName">修改用户名称</button>
    <div>
      ss
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      username: 'xiaoming'
    }
  },
  methods: {
    updateName() {
      this.username = '王五';
      console.log("当前。。。 ： ", this)
    }
  },
  watch: {
    data(newValue, oldValue) {

    }
  },
  computed: {
    name() {
      return this.data;
    }
  },

}
</script>

<style lang="less">
.test-bom {
  background-color: pink;

  h1 {
    color: #ff8a00;
  }
}
</style>