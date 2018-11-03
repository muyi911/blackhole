<template>
    <div>
        <button @click="back">返回</button>
    </div>
</template>
<script>
export default {
    methods:{
        back: function(){
            this.$router.push('loading');
        }
    }
}
</script>
