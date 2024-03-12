PGDMP      0                |            veterinaryFinal    16.1    16.1 '               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    21087    veterinaryFinal    DATABASE     �   CREATE DATABASE "veterinaryFinal" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
 !   DROP DATABASE "veterinaryFinal";
                postgres    false            �            1259    21157    animal    TABLE     >  CREATE TABLE public.animal (
    id bigint NOT NULL,
    animal_breed character varying(255),
    animal_colour character varying(255),
    animal_date_of_birth date,
    animal_gender character varying(255),
    animal_name character varying(255),
    animal_species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    21195 
   animal_seq    SEQUENCE     t   CREATE SEQUENCE public.animal_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.animal_seq;
       public          postgres    false            �            1259    21164    appointment    TABLE     �   CREATE TABLE public.appointment (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    21196    appointment_seq    SEQUENCE     y   CREATE SEQUENCE public.appointment_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.appointment_seq;
       public          postgres    false            �            1259    21169    available_date    TABLE     n   CREATE TABLE public.available_date (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    21197    available_date_seq    SEQUENCE     |   CREATE SEQUENCE public.available_date_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.available_date_seq;
       public          postgres    false            �            1259    21174    customer    TABLE     !  CREATE TABLE public.customer (
    id bigint NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_mail character varying(255) NOT NULL,
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255)
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    21198    customer_seq    SEQUENCE     v   CREATE SEQUENCE public.customer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.customer_seq;
       public          postgres    false            �            1259    21181    doctor    TABLE       CREATE TABLE public.doctor (
    id bigint NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255) NOT NULL,
    doctor_name character varying(255) NOT NULL,
    doctor_phone character varying(255) NOT NULL
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    21199 
   doctor_seq    SEQUENCE     t   CREATE SEQUENCE public.doctor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.doctor_seq;
       public          postgres    false            �            1259    21188    vaccine    TABLE     �   CREATE TABLE public.vaccine (
    id bigint NOT NULL,
    vaccine_code character varying(255),
    vaccine_name character varying(255),
    vaccine_finish_date date,
    vaccine_start_date date,
    animal_id bigint
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    21200    vaccine_seq    SEQUENCE     u   CREATE SEQUENCE public.vaccine_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.vaccine_seq;
       public          postgres    false            	          0    21157    animal 
   TABLE DATA           �   COPY public.animal (id, animal_breed, animal_colour, animal_date_of_birth, animal_gender, animal_name, animal_species, customer_id) FROM stdin;
    public          postgres    false    215   3-       
          0    21164    appointment 
   TABLE DATA           Q   COPY public.appointment (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    216   .                 0    21169    available_date 
   TABLE DATA           G   COPY public.available_date (id, available_date, doctor_id) FROM stdin;
    public          postgres    false    217   e.                 0    21174    customer 
   TABLE DATA           u   COPY public.customer (id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    218   �.                 0    21181    doctor 
   TABLE DATA           i   COPY public.doctor (id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    219   �/                 0    21188    vaccine 
   TABLE DATA           u   COPY public.vaccine (id, vaccine_code, vaccine_name, vaccine_finish_date, vaccine_start_date, animal_id) FROM stdin;
    public          postgres    false    220   �0                  0    0 
   animal_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.animal_seq', 51, true);
          public          postgres    false    221                       0    0    appointment_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.appointment_seq', 51, true);
          public          postgres    false    222                       0    0    available_date_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.available_date_seq', 51, true);
          public          postgres    false    223                       0    0    customer_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.customer_seq', 51, true);
          public          postgres    false    224                       0    0 
   doctor_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.doctor_seq', 51, true);
          public          postgres    false    225                        0    0    vaccine_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.vaccine_seq', 51, true);
          public          postgres    false    226            j           2606    21163    animal animal_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    215            l           2606    21168    appointment appointment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    216            n           2606    21173 "   available_date available_date_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    217            p           2606    21180    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    218            r           2606    21187    doctor doctor_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    219            t           2606    21194    vaccine vaccine_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    220            v           2606    21206 '   appointment fk2kkeptdxfuextg5ch7xp3ytie    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie;
       public          postgres    false    216    4714    215            u           2606    21201 "   animal fk6pvxm5gfjqxclb651be9unswe    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 L   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk6pvxm5gfjqxclb651be9unswe;
       public          postgres    false    218    215    4720            x           2606    21216 *   available_date fkk0d6pu1wxarsoou0x2e1cc2on    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on;
       public          postgres    false    217    4722    219            y           2606    21221 #   vaccine fkne3kmh8y5pcyxwl4u2w9prw6j    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j;
       public          postgres    false    4714    215    220            w           2606    21211 '   appointment fkoeb98n82eph1dx43v3y2bcmsl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl;
       public          postgres    false    216    4722    219            	   �   x�M��j�0���y�i��ڌ��Bi���?���W�����P�I|�#y�m@_�4��.�[oٖ�*�JZ���G��9S�!J�T+�˭[�]���n��1%�#Ւțm���š�w�n��TH�7�,���qi/���T��v�1���Ą�*�ֳ]ݤ�O����y�W�P	�޽4k��d[�(u#�F�Re��1���M]      
   G   x�Eɱ�0�����?a��?G7��;��B��Y�1�3�q��Ȅ:CϞ��
}F�9����\ ^�A         3   x�EǱ  �:�E(���簓�>�P��ʃ�~=M�5�=5�w�|��         �   x�U��n�0 ������B{�T��^z1�NF5���j$n��=;�(N���X͎h�jtz�|k2-\m7��ґ~U?�����!�Z�A���nE~�gu{z:2��!������ۚ<Ɗ1�� �� ��P$����y��J5�Y,���2�!�m�6���i�pP�����$�w�>���h����Զ�������5L��=
}i(2�����v��4��m��F�;=o�d��́U>�C�%�{         !  x�=�MN�0���s�X��]JA�A�Eꆍqlɱ��	
�ǉ�����{�B*�L��w9CM�d�%��ؙ��n��~�L��+��K��u��c�� D�fyR��,�L�I���������:���oe���JY���˒b�����E��!�8������i�I�0��;m$�d [��%�"����z����
�E�D��P����3^�l�h�'8/5�D�^?�ǯ|�^�V,�N�%X	��zi�q"7��]ַ�����T|ηT���;Yǂ�%^͸:�d�>9c��Ԇ	         �   x�m�=
�0Fg����sCȐ!t���DӴ�I�_��CK@�=����������s�z�쯝��S~�
�P�����D�Kn�0%���G>�I{�5j�&7��s���Ih���h�@�%���.l��d_'}�T�3Z������h�~���-�f��vA�/��J�     