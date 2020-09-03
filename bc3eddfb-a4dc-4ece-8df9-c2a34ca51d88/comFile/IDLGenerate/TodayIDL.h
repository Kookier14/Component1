

/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from TodayIDL.idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

#ifndef TodayIDL_1467360290_h
#define TodayIDL_1467360290_h

#ifndef NDDS_STANDALONE_TYPE
#ifndef ndds_cpp_h
#include "ndds/ndds_cpp.h"
#endif
#else
#include "ndds_standalone_type.h"
#endif

extern "C" {

    extern const char *PostDataTYPENAME;

}

struct PostDataSeq;
#ifndef NDDS_STANDALONE_TYPE
class PostDataTypeSupport;
class PostDataDataWriter;
class PostDataDataReader;
#endif

class PostData 
{
  public:
    typedef struct PostDataSeq Seq;
    #ifndef NDDS_STANDALONE_TYPE
    typedef PostDataTypeSupport TypeSupport;
    typedef PostDataDataWriter DataWriter;
    typedef PostDataDataReader DataReader;
    #endif

    DDS_Char *   x ;

};
#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, start exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport __declspec(dllexport)
#endif

NDDSUSERDllExport DDS_TypeCode* PostData_get_typecode(void); /* Type code */

DDS_SEQUENCE(PostDataSeq, PostData);                                        

NDDSUSERDllExport
RTIBool PostData_initialize(
    PostData* self);

NDDSUSERDllExport
RTIBool PostData_initialize_ex(
    PostData* self,RTIBool allocatePointers,RTIBool allocateMemory);

NDDSUSERDllExport
RTIBool PostData_initialize_w_params(
    PostData* self,
    const struct DDS_TypeAllocationParams_t * allocParams);        

NDDSUSERDllExport
void PostData_finalize(
    PostData* self);

NDDSUSERDllExport
void PostData_finalize_ex(
    PostData* self,RTIBool deletePointers);

NDDSUSERDllExport
void PostData_finalize_w_params(
    PostData* self,
    const struct DDS_TypeDeallocationParams_t * deallocParams);

NDDSUSERDllExport
void PostData_finalize_optional_members(
    PostData* self, RTIBool deletePointers);  

NDDSUSERDllExport
RTIBool PostData_copy(
    PostData* dst,
    const PostData* src);

#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, stop exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport
#endif
extern "C" {

    extern const char *uiadd_retTYPENAME;

}

struct uiadd_retSeq;
#ifndef NDDS_STANDALONE_TYPE
class uiadd_retTypeSupport;
class uiadd_retDataWriter;
class uiadd_retDataReader;
#endif

class uiadd_ret 
{
  public:
    typedef struct uiadd_retSeq Seq;
    #ifndef NDDS_STANDALONE_TYPE
    typedef uiadd_retTypeSupport TypeSupport;
    typedef uiadd_retDataWriter DataWriter;
    typedef uiadd_retDataReader DataReader;
    #endif

    DDS_Long   result ;
    DDS_Long   a ;

};
#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, start exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport __declspec(dllexport)
#endif

NDDSUSERDllExport DDS_TypeCode* uiadd_ret_get_typecode(void); /* Type code */

DDS_SEQUENCE(uiadd_retSeq, uiadd_ret);                                        

NDDSUSERDllExport
RTIBool uiadd_ret_initialize(
    uiadd_ret* self);

NDDSUSERDllExport
RTIBool uiadd_ret_initialize_ex(
    uiadd_ret* self,RTIBool allocatePointers,RTIBool allocateMemory);

NDDSUSERDllExport
RTIBool uiadd_ret_initialize_w_params(
    uiadd_ret* self,
    const struct DDS_TypeAllocationParams_t * allocParams);        

NDDSUSERDllExport
void uiadd_ret_finalize(
    uiadd_ret* self);

NDDSUSERDllExport
void uiadd_ret_finalize_ex(
    uiadd_ret* self,RTIBool deletePointers);

NDDSUSERDllExport
void uiadd_ret_finalize_w_params(
    uiadd_ret* self,
    const struct DDS_TypeDeallocationParams_t * deallocParams);

NDDSUSERDllExport
void uiadd_ret_finalize_optional_members(
    uiadd_ret* self, RTIBool deletePointers);  

NDDSUSERDllExport
RTIBool uiadd_ret_copy(
    uiadd_ret* dst,
    const uiadd_ret* src);

#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, stop exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport
#endif
extern "C" {

    extern const char *uiadd_paramsTYPENAME;

}

struct uiadd_paramsSeq;
#ifndef NDDS_STANDALONE_TYPE
class uiadd_paramsTypeSupport;
class uiadd_paramsDataWriter;
class uiadd_paramsDataReader;
#endif

class uiadd_params 
{
  public:
    typedef struct uiadd_paramsSeq Seq;
    #ifndef NDDS_STANDALONE_TYPE
    typedef uiadd_paramsTypeSupport TypeSupport;
    typedef uiadd_paramsDataWriter DataWriter;
    typedef uiadd_paramsDataReader DataReader;
    #endif

    DDS_Long   a ;
    DDS_Long   b ;

};
#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, start exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport __declspec(dllexport)
#endif

NDDSUSERDllExport DDS_TypeCode* uiadd_params_get_typecode(void); /* Type code */

DDS_SEQUENCE(uiadd_paramsSeq, uiadd_params);                                        

NDDSUSERDllExport
RTIBool uiadd_params_initialize(
    uiadd_params* self);

NDDSUSERDllExport
RTIBool uiadd_params_initialize_ex(
    uiadd_params* self,RTIBool allocatePointers,RTIBool allocateMemory);

NDDSUSERDllExport
RTIBool uiadd_params_initialize_w_params(
    uiadd_params* self,
    const struct DDS_TypeAllocationParams_t * allocParams);        

NDDSUSERDllExport
void uiadd_params_finalize(
    uiadd_params* self);

NDDSUSERDllExport
void uiadd_params_finalize_ex(
    uiadd_params* self,RTIBool deletePointers);

NDDSUSERDllExport
void uiadd_params_finalize_w_params(
    uiadd_params* self,
    const struct DDS_TypeDeallocationParams_t * deallocParams);

NDDSUSERDllExport
void uiadd_params_finalize_optional_members(
    uiadd_params* self, RTIBool deletePointers);  

NDDSUSERDllExport
RTIBool uiadd_params_copy(
    uiadd_params* dst,
    const uiadd_params* src);

#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, stop exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport
#endif

#endif /* TodayIDL */

