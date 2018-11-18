const global = {
    message(type, msg) {
        switch (type) {
            case 'normal':
                bus.$message(msg)
                break
            case 'success':
                bus.$message({
                    type: type,
                    message: msg
                })
                break
            case 'warning':
                bus.$message({
                    type: type,
                    message: msg
                })
                break
            case 'error':
                bus.$message({
                    type: type,
                    message: msg
                })
                break
        }
    }
}

export default global