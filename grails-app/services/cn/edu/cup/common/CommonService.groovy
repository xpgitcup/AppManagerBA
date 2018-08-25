package cn.edu.cup.common

class CommonService {

    /*
    * 上传文件
    * */
    File upload(params) {
        //println("service: ${params}")
        //println("service: ${params.uploadedFile}")
        //println("service: ${params.uploadedFile.originalFilename}")
        if (params.uploadedFile && params.destDir) {
            def uploadedFile = params.uploadedFile
            def destDir = params.destDir
            def userDir = new File(destDir)
            if (!userDir.exists()) {
                userDir.mkdirs()
            }
            def destFile = new File(userDir, uploadedFile.originalFilename)
            uploadedFile.transferTo(destFile)
            println "upload ${destFile}"
            return destFile
        }
    }

    /*
    * 下载文件
    * */
    def downLoadFile(params) {
        def hasError = []
        if (params.downLoadFileName) {
            def filename = params.downLoadFileName
            def sf = new File(filename)
            println "download: ${sf} -- ${filename}"
            if (sf.exists()) {
                println "begin download......"
                def fName = sf.getName()
                // 处理中文乱码
                def name = URLEncoder.encode(fName, "UTF-8");
                def response = getResponse()
                response.setHeader("Content-disposition", "attachment; filename=" + name)
                response.contentType = "application/x-rarx-rar-compressed"
                //response.contentType = ""

                def out = response.outputStream
                def inputStream = new FileInputStream(sf)
                byte[] buffer = new byte[1024]
                int i = -1
                while ((i = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, i)
                }
                out.flush()
                out.close()
                inputStream.close()
            } else {
                hasError.add("文件不存在.")
            }
        } else {
            hasError.add("缺少downLoadFileName参数.")
        }
    }

    //Getting the Request object
    def getRequest() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        webUtils.getCurrentRequest()
    }

    //Getting the Response object
    def getResponse() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        webUtils.getCurrentResponse()
    }
    //Getting the ServletContext object
    def getServletContext() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        webUtils.getServletContext()
    }
    //Getting the Session object
    def getSession() {
        RequestContextHolder.currentRequestAttributes().getSession()
    }

    //获取当前程序的根目录
    def getApplicationRoot() {
        return getServletContext().getRealPath("/")
    }

    //获取当前程序名称
    def getApplicationName() {
        return grails.util.Metadata.current.'app.name'
    }

    //获取当前网站的根目录
    def getWebRootDir() {
        def appRoot = getApplicationRoot()
        def appName = getApplicationName()
        def kk = appRoot.lastIndexOf(appName)
        def webRoot
        if (kk>-1) {
            webRoot = appRoot.substring(0, kk)
        } else {
            webRoot = appRoot
        }
        return webRoot
    }

    /*
    * 根据环境检查文件路径
    * */
    def checkFilePath4Enviroment(pathString) {
        def webRoot = getWebRootDir()
        def result
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                result = pathString
                break
            case Environment.PRODUCTION:
                result = "${webRoot}/WEB-INF/${pathString}"
                break
        }
        return result
    }


    //2014.12.22 -- 标记当前页面的偏移量
    def processOffset(session, params) {
        //如果没有offset，尝试读取原来的offset--,如果有了，记住他们
        def key = calculateCurrentKey(params)
        println "processOffset ${params}"
        if (params.containsKey('offset')) {
            println "主动设置偏移量..."
            session.putValue(key, params.offset)
        } else {
            println "查询上次的偏移量..."
            params.offset = session.getValue(key)
        }
    }

    //删除记录后，必须重置偏移量
    def resetOffsetAfterDelete(session, params) {
        def key = calculateCurrentKey(params)
        session.putValue(key, 0)
    }

    def calculateCurrentKey(params) {
        def key = params.controller + "_" + params.action + "_offset"
        return key
    }

}
