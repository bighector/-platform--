<template>
  <div class="category-box">
      <div class="category-box-header">
          <div class="header-line">
              <span class="line-text">{{title}}</span>
          </div>
      </div>
      <div class="category-box-view"></div>
  </div>
</template>

<script>
export default {
  name:'category-box',
  props:{
      title:{
          required:true,
          type:String
      }
  }
}
</script>


<style lang="less" scoped>
@import '../../../../assets/css/var.less';
.category-box{
    .category-box-header{
        position: relative;
        height: 45px;
        width:100%;
        .header-line{
            position: absolute;
            top:50%;
            left:50%;
            width:60%;
            transform: translate(-50%,-50%);
            height: 1px;
            background:@border-color;
            .line-text{
                position: absolute;
                top:50%;
                left:50%;
                transform: translate(-50%,-50%);
                font-size: 12px;
                background: white;
                padding:0px 10px;
                color:@content-color;
            }
        }
    }
}
</style>

