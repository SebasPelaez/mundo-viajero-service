CREATE DATABASE mundoviajero
    WITH 
    OWNER = udearoot
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE public."State"
(
    "Id" bigint NOT NULL,
    "Description" character(50) NOT NULL,
    "BelongsTo" character(50) NOT NULL,
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_STATE"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_STATE" OWNER TO udearoot;

CREATE TABLE public."Profile"
(
    "Id" bigint NOT NULL,
    "Description" character(100) NOT NULL,
    "StateId" bigint NOT NULL references public."State"("Id"),
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_PROFILE"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_PROFILE" OWNER TO udearoot;

CREATE TABLE public."Person"
(
    "Id" bigint NOT NULL,
    "Identification" character(20),
    "RNT" character(25),
    "Name" character(100) NOT NULL,
    "LastName" character(100) NOT NULL,
    "Birthday" date NOT NULL,
    "Email" character(45) NOT NULL,
    "PhoneNumber" character(13) NOT NULL,
    "Address" character(150),
    "Password" character(500),
    "Calification" double precision,
    "Token" character(500),
    "ProfilePhoto" character(500),
    "ProfileId" bigint NOT NULL references public."Profile"("Id"),
    "StateId" bigint NOT NULL references public."State"("Id"),
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_PERSON"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_PERSON" OWNER TO udearoot;

CREATE TABLE public."Event"
(
    "Id" bigint NOT NULL,
    "Name" character(150) NOT NULL,
    "Description" character(500) NOT NULL,
    "StartDate" timestamp(4) without time zone NOT NULL,
    "EndDate" timestamp(4) without time zone NOT NULL,
    "AltitudeMeetingPoint" character(250),
    "LatitudeMeetingPoint" character(250),
    "Capaciticy" integer NOT NULL,
    "Fare" double precision NOT NULL,
    "PersonIdResponsible" bigint NOT NULL references public."Person"("Id"),
    "StateId" bigint NOT NULL references public."State"("Id"),
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_EVENT"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_EVENT" OWNER TO udearoot;

CREATE TABLE public."Recomendation"
(
    "Id" bigint NOT NULL,
    "Description" character(200) NOT NULL,
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_RECOMENDATION"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_RECOMENDATION" OWNER TO udearoot;

CREATE TABLE public."EventRecomendation"
(
    "EventId" bigint NOT NULL references public."Event"("Id"),
    "RecomendationId" bigint NOT NULL references public."Recomendation"("Id"),
    PRIMARY KEY ("EventId","RecomendationId")
);

CREATE TABLE public."ImageEvent"
(
    "Id" bigint NOT NULL,
    "EventId" bigint NOT NULL references public."Event"("Id"),
    "UploadDate" timestamp(4) without time zone NOT NULL,
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_IMAGEEVENT"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_IMAGEEVENT" OWNER TO udearoot;

CREATE TABLE public."Atendee"
(
    "PersonId" bigint NOT NULL references public."Person"("Id"),
    "EventId" bigint NOT NULL references public."Event"("Id"),
    "EnrollDate" timestamp(4) without time zone NOT NULL,
    "StateId" bigint NOT NULL references public."State"("Id"),
    PRIMARY KEY ("PersonId","EventId")
);

CREATE TABLE public."Department"
(
    "Id" bigint NOT NULL,
    "Name" character(100) NOT NULL,
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_DEPARTMENT"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_DEPARTMENT" OWNER TO udearoot;

CREATE TABLE public."City"
(
    "Id" bigint NOT NULL,
    "Name" character(100) NOT NULL,
    "DepartmentId" bigint NOT NULL references public."Department"("Id"),
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_CITY"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_CITY" OWNER TO udearoot;

CREATE TABLE public."EventPlace"
(
    "Id" bigint NOT NULL,
    "EventId" bigint NOT NULL references public."Event"("Id"),
    "CityId" bigint NOT NULL references public."City"("Id"),
    "EventPlaceStartDate" timestamp(4) without time zone NOT NULL,
    "EventPlaceEndDate" timestamp(4) without time zone NOT NULL,
    "AltitudeEventPlace" character(250) NOT NULL,
    "LatitudeEventPlace" character(250)  NOT NULL,
    PRIMARY KEY ("Id")
);

CREATE SEQUENCE public."SQ_EVENTPLACE"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public."SQ_EVENTPLACE" OWNER TO udearoot;


ALTER TABLE public."State" OWNER to udearoot;
ALTER TABLE public."Profile" OWNER to udearoot;
ALTER TABLE public."Person" OWNER to udearoot;
ALTER TABLE public."Event" OWNER to udearoot;
ALTER TABLE public."Recomendation" OWNER to udearoot;
ALTER TABLE public."EventRecomendation" OWNER to udearoot;
ALTER TABLE public."ImageEvent" OWNER to udearoot;
ALTER TABLE public."EventPlace" OWNER to udearoot;
ALTER TABLE public."Atendee" OWNER to udearoot;
ALTER TABLE public."Department" OWNER to udearoot;
ALTER TABLE public."City" OWNER to udearoot;

