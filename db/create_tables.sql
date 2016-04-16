CREATE SCHEMA ili2gpkg_service
  AUTHORIZATION stefan;
GRANT ALL ON SCHEMA ili2gpkg_service TO stefan;
GRANT USAGE ON SCHEMA ili2gpkg_service TO mspublic;


CREATE TABLE ili2gpkg_service.translation
(
  ogc_fid serial NOT NULL,
  file_path character varying NOT NULL,
  upload_date timestamp NOT NULL DEFAULT NOW(),
  --job_id integer,
  --job_started timestamp,
  --job_finished timestamp,
  --trans_id integer, --braucht eventuell nicht -> ogc_fid?
  download_url character varying(255),
  CONSTRAINT translation_pkey PRIMARY KEY (ogc_fid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ili2gpkg_service.translation OWNER TO stefan;
GRANT ALL ON TABLE ili2gpkg_service.translation TO stefan;
GRANT SELECT ON TABLE ili2gpkg_service.translation TO mspublic;


CREATE TABLE ili2gpkg_service.job
(
  ogc_fid serial NOT NULL,
  state character varying(11) NOT NULL DEFAULT 'NOT_STARTED',
  job_started timestamp,
  job_finished timestamp,
  CONSTRAINT job_pkey PRIMARY KEY (ogc_fid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ili2gpkg_service.job OWNER TO stefan;
GRANT ALL ON TABLE ili2gpkg_service.job TO stefan;
GRANT SELECT ON TABLE ili2gpkg_service.job TO mspublic;
