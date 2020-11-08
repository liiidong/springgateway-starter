package com.enough.gateway.db;

/**
 * @since 统一使用alibaba的druid
 */
//@ConfigurationProperties(prefix="spring.datasource")
@Deprecated
public class DatasourceConnInfo {

    public static String TYPE_LOCATION_HOLDER = "{TYPE}";
    private boolean enable = true;
    
    private DataBaseType dbType;
    private String dbName;
	private String username;
	private String password;
	private String server;
	private String port;
	private String mapperLocations= "classpath*:com/zxt/**/" + TYPE_LOCATION_HOLDER + "/*Mapper.xml";
	private int initialPoolSize = 3;
	private int maxPoolSize = 10;
	private int minPoolSize = 3;
	private int maxIdleTime = 1000;
	private int maxWait = 300;
	private int checkoutTimeout = 30000;
	
    public DataBaseType getDbType() {
        return dbType;
    }
    public void setDbType(DataBaseType dbType) {
        this.dbType = dbType;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public int getInitialPoolSize() {
        return initialPoolSize;
    }
    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    public int getMinPoolSize() {
        return minPoolSize;
    }
    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }
    public int getMaxIdleTime() {
        return maxIdleTime;
    }
    public void setMaxIdleTime(int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }
    public int getMaxWait() {
        return maxWait;
    }
    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
    public String getMapperLocations() {
        return mapperLocations;
    }
    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public boolean isEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public int getCheckoutTimeout() {
        return checkoutTimeout;
    }
    public void setCheckoutTimeout(int checkoutTimeout) {
        this.checkoutTimeout = checkoutTimeout;
    }
}
