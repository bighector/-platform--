<template>
  <div class="category">
    <div class="category-header">
      <div class="header-search">
        <input type="text" placeholder="商品搜索，共有239款好物">
      </div>
      <div class="header-tabbar">
        <tab>
          <tab-item selected @on-item-click="typeHandler">不限</tab-item>
          <tab-item @on-item-click="typeHandler">今日特价</tab-item>
          <tab-item @on-item-click="typeHandler">活动</tab-item>
        </tab>
      </div>
    </div>
    <div class="category-view">
      <template v-if="typeActiveIndex === 0">
        <div class="view-tabbar">
          <ul>
            <li
            :key="k"
            :class="{'tabbar-acitve':tabbarActiveIndex === k}"
            @click="selectCate(k,v.id)"
            v-for="(v,k) in cateList"
            >{{v.name}}</li>
          </ul>
        </div>
        <div class="view-content">
          <div class="content-brand">
            <img src="//img13.360buyimg.com/mcoss/jfs/t14161/280/1433362511/47508/aaf4a790/5a1fffe7Nfebc56da.jpg">
          </div>
          <div class="content-category">
            <category-box title="志趣分类"></category-box>
          </div>
        </div>
      </template>
      <template v-if="typeActiveIndex === 1"></template>
      <template v-if="typeActiveIndex === 2"></template>
    </div>
  </div>
</template>

<script>
import { Tab, TabItem } from "vux";
export default {
  data(){
    return{
      cateList:[
        {
          id:1,
          name:'志趣'
        },
        {
          id:2,
          name:'洗货'
        },
        {
          id:3,
          name:'杂货'
        },
        {
          id:4,
          name:'婴童'
        },
        {
          id:5,
          name:'服装'
        }
      ],
      typeActiveIndex:0,
      tabbarActiveIndex:0,
    }
  },
  components: {
    Tab,
    TabItem
  },
  methods:{
    typeHandler(index){
      this.typeActiveIndex = index;
    },
    selectCate(key,id){
      this.tabbarActiveIndex = key;
    }
  }
};
</script>

<style lang="less" scoped>
@import "../../assets/css/var.less";
.category {
  position: absolute;
  top: 46px;
  left: 0px;
  right: 0px;
  bottom: 53px;
  display: flex;
  flex-direction: column;
  background: white;
  .category-header {
    flex-grow: 0;
    border-top: 1px solid @divider-color;
    padding: 0px 10px;
    .header-search {
      padding: 10px 0px;
      input {
        display: block;
        border: none;
        width: 100%;
        height: 25px;
        background: @divider-color;
        outline: none;
        line-height: 25px;
        border-radius: 5px;
        text-align: center;
      }
    }
  }
  .category-view {
    flex: 1;
    display: flex;
    .view-tabbar{
      flex-basis: 70px;
      overflow-x: hidden;
      overflow-y: auto;
      box-sizing: border-box;
      border-right:1px solid  @divider-color;
      ul{
        display: flex;
        flex-direction: column;
        list-style: none;
        li{
          position: relative;
          flex-basis:35px;
          flex-grow: 0;
          line-height: 35px;
          text-align: center;
          font-size: 14px;
          color:@content-color;
        }
        .tabbar-acitve{
          color:@success-color;
          &::after{
            position: absolute;
            content: '';
            top:0px;
            left:0px;
            height: 100%;
            width: 2px;
            background: @success-color;
          }
        }
      }
    }
    .view-content{
      flex:1;
      overflow-x: hidden;
      overflow-y: auto;
      padding:0px 10px;
      .content-brand{
        width:100%;
        height: 100px;
        padding:15px 0px;
        img{
          display: block;
          width:100%;
          height: 100%;
        }
      }
    }
  }
}
</style>

