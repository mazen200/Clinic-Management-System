-- =============================================================
-- Clinic Management System — Seed Data (PostgreSQL)
-- DB: clinic_db
--
-- HOW TO RUN:
--   psql -U postgres -d clinic_db -f seed_data.sql
--
-- NOTE: Patient.id uses GenerationType.IDENTITY now, so
--       PostgreSQL handles auto-increment via IDENTITY column.
--       All other entities also use IDENTITY.
-- =============================================================

-- ── Clean slate (order respects FK constraints) ──────────────
TRUNCATE TABLE bills, appointments, doctor_schedules, doctors,
               patients, users, specializations RESTART IDENTITY CASCADE;

-- ────────────────────────────────────────────────────────────────
-- 1. Specializations
-- ────────────────────────────────────────────────────────────────
INSERT INTO specializations (name, description) VALUES
  ('Cardiology',   'Heart and cardiovascular system'),
  ('Neurology',    'Brain, spinal cord and nervous system'),
  ('Orthopedics',  'Bones, joints, ligaments and tendons'),
  ('Dermatology',  'Skin, hair and nail conditions'),
  ('Pediatrics',   'Medical care for infants and children');
-- IDs will be: 1, 2, 3, 4, 5

-- ────────────────────────────────────────────────────────────────
-- 2. Users
-- ────────────────────────────────────────────────────────────────
INSERT INTO users (email, password, role) VALUES
  ('dr.ahmed@clinic.com',   '$2a$10$dummyhash1', 'DOCTOR'),   -- id 1
  ('dr.sara@clinic.com',    '$2a$10$dummyhash2', 'DOCTOR'),   -- id 2
  ('dr.omar@clinic.com',    '$2a$10$dummyhash3', 'DOCTOR'),   -- id 3
  ('dr.layla@clinic.com',   '$2a$10$dummyhash4', 'DOCTOR'),   -- id 4
  ('dr.khaled@clinic.com',  '$2a$10$dummyhash5', 'DOCTOR'),   -- id 5
  ('ali.hassan@mail.com',   '$2a$10$dummyhash6', 'PATIENT'),  -- id 6
  ('nour.mahmoud@mail.com', '$2a$10$dummyhash7', 'PATIENT'),  -- id 7
  ('youssef.ali@mail.com',  '$2a$10$dummyhash8', 'PATIENT'),  -- id 8
  ('hana.samy@mail.com',    '$2a$10$dummyhash9', 'PATIENT'),  -- id 9
  ('tarek.adel@mail.com',   '$2a$10$dummyhash10','PATIENT');  -- id 10

-- ────────────────────────────────────────────────────────────────
-- 3. Doctors
-- ────────────────────────────────────────────────────────────────
INSERT INTO doctors (name, consultation_fee, is_active, created_at, user_id, specialization_id) VALUES
  ('Dr. Ahmed Nasser',   250.00, true,  NOW() - INTERVAL '2 years',   1, 1),  -- doc id 1
  ('Dr. Sara Khalil',    300.00, true,  NOW() - INTERVAL '18 months', 2, 2),  -- doc id 2
  ('Dr. Omar Farouk',    200.00, true,  NOW() - INTERVAL '1 year',    3, 3),  -- doc id 3
  ('Dr. Layla Ibrahim',  350.00, false, NOW() - INTERVAL '3 years',   4, 4),  -- doc id 4
  ('Dr. Khaled Mansour', 275.00, true,  NOW() - INTERVAL '6 months',  5, 5);  -- doc id 5

-- ────────────────────────────────────────────────────────────────
-- 4. Patients
--    NOW uses IDENTITY column — no explicit id needed.
--    The @GeneratedValue(strategy = IDENTITY) fix in Patient.java
--    makes Hibernate and raw SQL both use the same PostgreSQL sequence.
-- ────────────────────────────────────────────────────────────────
INSERT INTO patients (name, phone, address, age, nationality, blood_type, is_active, user_id) VALUES
  ('Ali Hassan',   '01012345678', '12 Tahrir St, Cairo',           34, 'Egyptian', 'O+',  true,  6),  -- id 1
  ('Nour Mahmoud', '01123456789', '5 Nile Corniche, Giza',         28, 'Egyptian', 'A-',  true,  7),  -- id 2
  ('Youssef Ali',  '01234567890', '8 Victoria St, Alexandria',     45, 'Egyptian', 'B+',  true,  8),  -- id 3
  ('Hana Samy',    '01098765432', '3 Garden City, Cairo',          22, 'Egyptian', 'AB+', false, 9),  -- id 4
  ('Tarek Adel',   '01587654321', '19 Heliopolis Ave, Cairo',      55, 'Egyptian', 'A+',  true,  10); -- id 5

-- ────────────────────────────────────────────────────────────────
-- 5. Doctor Schedules
-- ────────────────────────────────────────────────────────────────
INSERT INTO doctor_schedules (doctor_id, day_of_week, start_time, end_time) VALUES
  (1, 'SUNDAY',    '09:00', '13:00'),  -- schedule id 1
  (1, 'TUESDAY',   '14:00', '18:00'),  -- schedule id 2
  (2, 'MONDAY',    '10:00', '14:00'),  -- schedule id 3
  (2, 'WEDNESDAY', '10:00', '14:00'),  -- schedule id 4
  (3, 'THURSDAY',  '08:00', '12:00'),  -- schedule id 5
  (3, 'SATURDAY',  '09:00', '13:00'),  -- schedule id 6
  (5, 'MONDAY',    '14:00', '18:00'),  -- schedule id 7
  (5, 'FRIDAY',    '09:00', '12:00');  -- schedule id 8

-- ────────────────────────────────────────────────────────────────
-- 6. Appointments
-- ────────────────────────────────────────────────────────────────
INSERT INTO appointments (appointment_date, appointment_time, status, created_at, doctor_id, patient_id) VALUES
  ('2026-08-01', '2026-08-01 09:00:00', 'CONFIRMED', CURRENT_DATE,     1, 1),  -- appt id 1
  ('2026-08-01', '2026-08-01 10:00:00', 'PENDING',   CURRENT_DATE,     2, 2),  -- appt id 2
  ('2026-07-30', '2026-07-30 08:00:00', 'COMPLETED', CURRENT_DATE - 1, 3, 3),  -- appt id 3
  ('2026-07-29', '2026-07-29 14:00:00', 'CANCELLED', CURRENT_DATE - 2, 1, 4),  -- appt id 4
  ('2026-08-02', '2026-08-02 14:00:00', 'PENDING',   CURRENT_DATE,     5, 5),  -- appt id 5
  ('2026-07-28', '2026-07-28 09:00:00', 'COMPLETED', CURRENT_DATE - 3, 2, 1),  -- appt id 6
  ('2026-08-03', '2026-08-03 10:00:00', 'CONFIRMED', CURRENT_DATE,     3, 2),  -- appt id 7
  ('2026-08-04', '2026-08-04 09:00:00', 'PENDING',   CURRENT_DATE,     5, 3);  -- appt id 8

-- ────────────────────────────────────────────────────────────────
-- 7. Bills  (for completed and confirmed appointments only)
-- ────────────────────────────────────────────────────────────────
INSERT INTO bills (appointment_id, total_amount, payment_status, payment_method, created_at, updated_at) VALUES
  (3, 200.00, 'PAID',    'CASH',        CURRENT_DATE - 1, CURRENT_DATE - 1),
  (6, 300.00, 'PAID',    'CREDIT_CARD', CURRENT_DATE - 3, CURRENT_DATE - 3),
  (1, 250.00, 'PENDING', 'CASH',        CURRENT_DATE,     CURRENT_DATE),
  (7, 200.00, 'PENDING', 'CREDIT_CARD', CURRENT_DATE,     CURRENT_DATE);

-- ────────────────────────────────────────────────────────────────
-- Verification queries — uncomment to inspect the data
-- ────────────────────────────────────────────────────────────────
-- SELECT * FROM specializations ORDER BY id;
-- SELECT d.id, d.name, d.is_active, s.name AS spec FROM doctors d JOIN specializations s ON d.specialization_id = s.id;
-- SELECT id, name, phone, age, is_active FROM patients ORDER BY id;
-- SELECT ds.id, d.name AS doctor, ds.day_of_week, ds.start_time, ds.end_time FROM doctor_schedules ds JOIN doctors d ON ds.doctor_id = d.id ORDER BY d.name;
-- SELECT a.id, p.name AS patient, d.name AS doctor, a.status, a.appointment_date FROM appointments a JOIN patients p ON a.patient_id = p.id JOIN doctors d ON a.doctor_id = d.id;
-- SELECT b.id, a.id AS appt_id, b.total_amount, b.payment_status, b.payment_method FROM bills b JOIN appointments a ON b.appointment_id = a.id;
