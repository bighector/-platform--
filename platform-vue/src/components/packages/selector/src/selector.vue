<template>
  <div class="selector" :class="{'active':value}" @click="emitValue">
      <div class="selector-wrap">
            <i class="iconfont" v-html="innerText"></i>
      </div>
  </div>
</template>

<script>
export default {
  name:'selector',
  data(){
      return{
          value:this.selected
      }
  },
  props:{
      selected:{
          type:Boolean,
          default:false
      }
  },
  computed:{
      innerText(){
          return this.value ? '&#xe620;' : '&#xe6d1;'
      }
  },
  methods:{
      emitValue(){
          this.value = !this.value;
          this.$emit('handler',this.value)
      }
  }
}
</script>

<style lang="less" scoped>
@import '../../../../assets/css/var.less';
.selector{
    display: inline-block;
    .selector-wrap{
        display: flex;
        width:25px;
        height: 25px;
        align-items: center;
        justify-content: center;
        overflow: hidden;
        i{
            font-size: 20px;
        }
    }
}
.active{
    i{
        color:@success-color;
    }
}
</style>


