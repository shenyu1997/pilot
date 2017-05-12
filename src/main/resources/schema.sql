DROP TABLE T_TENANTS;

CREATE TABLE T_TENANTS (
  ID VARCHAR ,
  NAME VARCHAR ,
  STATE VARCHAR
);

DROP TABLE T_TENANT_OPERATIONS;

CREATE TABLE T_TENANT_OPERATIONS (
  ID VARCHAR ,
  TENANT_ID VARCHAR,
  ACTION VARCHAR,
  ACTION_TIME DATE,
  IS_SUCCESSFUL VARCHAR
)